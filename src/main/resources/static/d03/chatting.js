console.log("chatting.js run");
const client = new WebSocket("/chat");

// 익명 채팅 (비회원제)
// 이름 (난수 처리) -> floor(Math.random * 끝값) + 시작값;
const randomID = Math.floor(Math.random() * 1000) + 1;
const nickName = `익명${randomID}`;
// 방 번호 (queryString, 없으면 0)
const params = new URL(location.href).searchParams;
const room = params.get('room') || "0";

client.onopen = () => {
    console.log("open");
    // 방 번호에 특정한 닉네임 등록 메세지
    // stringify() : JSON 형식을 유지하고 문자열 타입으로 변환, parse() : 문자열 타입을 JSON 타입으로 변환
    let msg = { type : "join", room : room, nickName : nickName };
    client.send(JSON.stringify(msg));
}

client.onclose = () => {
    console.log("close");
}

client.onmessage = (event) => {
    console.log("message : " + event.data);

    // 받은 메세지를 JSON으로 변환
    const message = JSON.parse(event.data);
    // 메세지 타입을 확인하여 html 생성
    let html = '';
    if (message.type == 'alarm') {
        html += `<div class="alarm">
                    <span> ${message.message} </span>
                </div>`;
    } else if (message.type == 'msg') {
        if (message.from == nickName) { // 내가 보낸 메세지
            html += `<div class="secontent">
                        <div class="date"> ${message.date} </div>
                        <div class="content"> ${message.message} </div>
                    </div>`;
        } else { // 남이 보낸 메세지
            html += `<div class="receiveBox">
                        <div class="profileImg">
                            <img  src="default.jpg"/>
                        </div>
                        <div>
                            <div class="recontent">
                                <div class="memberNic"> ${message.from} </div>
                                <div class="subcontent">
                                    <div class="content"> ${message.message} </div>
                                    <div class="date"> ${message.date} </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
        }
    }
    document.querySelector(".msgbox").innerHTML += html;

    // 내용물이 고정 사이즈보다 커지면 스크롤 내리기
    const msgbox = document.querySelector(".msgbox");
    // DOM객체.scrollTop : 현재 마크업의 스크롤 상단 꼭지점 위치
    // DOM객체.scrollHeight : 현재 마크업의 스크롤 전체 길이
    // 현재 msgbox의 스크롤 위치를 가장 하단에 대입
    msgbox.scrollTop = msgbox.scrollHeight;
}

// 서버에게 일반 메세지 보내기
const onMsgSend = () => {
    const msginput = document.querySelector(".msginput");
    const message = msginput.value;
    // 값이 없으면 종료
    if (message == '') return;
    const msg = { type : 'msg', message : message, from : nickName, date : new Date().toLocaleString() };
    client.send(JSON.stringify(msg));
    msginput.value = '';
}

// input에서 엔터(13) 입력 시
const enterKey = () => {
    if (window.event.keyCode == 13) {
        onMsgSend(); // 함수 호출
    }
}