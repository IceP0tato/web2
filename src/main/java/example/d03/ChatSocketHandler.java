package example.d03;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
public class ChatSocketHandler extends TextWebSocketHandler {
    // 접속된 소켓 명단 (해당 방 (key)에 접속된 클라이언트들 (value))
    // { 0 : [ "유재석", "강호동" ] , 1 : [ "서장훈", "김희철" ] }
    private static final Map<String, List<WebSocketSession>> clientList = new Hashtable<>();

    // Map 컬렉션 : put(key, value), get(key) / List 컬렉션 : add(value), get(index)

    // 소켓 연동 이벤트
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

    }

    // 소켓 연동 해제 이벤트
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String room = (String)session.getAttributes().get("room");
        String nickName = (String)session.getAttributes().get("nickName");
        // 방과 닉네임이 일치한 데이터가 접속 명단에 존재하면
        if (room != null && nickName != null) {
            List<WebSocketSession> list = clientList.get(room); // 해당 방의 key 접속 목록
            list.remove(session);
            // 알림 메세지 보내기
            alarmMessage(room, nickName+"이 퇴장 했습니다.");
        }
    }

    // Restful API 인 @ResponseBody는 자동으로 JSON <-> MAP 변환하지만, 소켓은 지원하지 않음
    // JSON 형식을 MAP 타입으로 변환 (ObjectMapper)
    // readValue(json 문자열, 변환할 클래스); -> JSON to MAP
    // writeValueAsString(map 객체) -> MAP to JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 소켓 메세지 이벤트
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트 메세지
        System.out.println(message.getPayload());
        // readValue(json 문자열, 변환할 클래스);
        Map<String, String> msg = objectMapper.readValue(message.getPayload(), Map.class);
        if (msg.get("type").equals("join")) {
            String room = msg.get("room");
            String nickName = msg.get("nickName");
            // 클라이언트 소켓(세션)에 부가 정보 추가
            session.getAttributes().put("room", room);
            session.getAttributes().put("nickName", nickName);
            // 접속 명단에 등록
            if (clientList.containsKey(room)) { // 방 번호가 존재하면
                clientList.get(room).add(session); // 해당 방 번호에 세션 추가
            } else { // 방 번호가 존재하지 않으면
                List<WebSocketSession> list = new Vector<>();
                list.add(session); // 새로운 목록에 세션 추가
                clientList.put(room, list); // 새로운 방 번호, 새로운 목록을 접속 명단에 등록
            }
            // 알림 메세지 보내기
            alarmMessage(room, nickName+"이 입장 했습니다.");
        } else if (msg.get("type").equals("msg")) {
            // 같은 방에 위치한 모든 세션들에게 받은 메세지 보내기
            String room = (String)session.getAttributes().get("room");
            for (WebSocketSession client : clientList.get(room)) {
                client.sendMessage(message);
            }
        }
        System.out.println(clientList);
    }

    // 서비스 메소드 (방 번호, 메세지 내용)
    // throws : 예외처리 던지기 (해당 메소드에서 모든 예외/오류를 호출한 곳으로 반환)
    public void alarmMessage(String room, String message) throws Exception {
        // 보내고자 하는 정보를 map 타입으로 구성
        Map<String, String> msg = new HashMap<>();
        msg.put("type", "alarm");
        msg.put("message", message);

        // map 타입을 json 형식으로 변환
        String sendMsg = objectMapper.writeValueAsString(msg);
        // 현재 같은 방에 위치한 모든 세션들에게 알림 메세지
        for (WebSocketSession session : clientList.get(room)) {
            session.sendMessage(new TextMessage(sendMsg));
        }
    }
}
