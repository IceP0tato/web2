console.log("socket.js run");

// JS(클라이언트)가 SPRING(서버)에게 웹 소켓 접속/연결 요청
// 클라이언트 웹 소켓 객체 생성 (config 클래스에서 정의한 주소로 매핑)
// new WebSocket("ws://localhost:8080/chat");
const client = new WebSocket("/chat");

// 클라이언트 소켓 주요 메소드
// onopen() : 서버 소켓과 연결이 성공됐을 시 실행
client.onopen = (event) => {
    console.log("서버 소켓과 연동 성공");
}
// onclose() : 서버 소켓과 연결이 종료됐을 시 실행
client.onclose = (event) => {
    console.log("서버 소켓과 연동 종료");
} 
// onerror() : 서버 소켓과 에러가 발생했을 시 실행
client.onerror = (event) => {
    console.log("서버 소켓과 에러 발생");
}
// onmessage() : 서버 소켓으로부터 메세지를 받았을 시 실행
client.onmessage = (event) => {
    console.log("서버로부터 메세지 도착");
    console.log(event); // 서버로부터 받은 이벤트 정보 객체
    console.log(event.data); // 메세지

    const msgBox = document.querySelector(".msgBox");
    let html = `<div>${event.data}</div>`;
    msgBox.innerHTML += html;
}

// 전송 버튼 클릭 시
const onSend = async () => {
    const msg = document.querySelector(".msg").value;

    // 클라이언트 소켓이 연결된 서버 소켓에 메세지 보내기
    client.send(msg);
}