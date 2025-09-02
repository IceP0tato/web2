package example.d02;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component // 스프링 컨테이너에 빈 등록
public class ChatHandler extends TextWebSocketHandler {

    // 서버에 접속한 클라이언트 소켓 명단
    // ArrayList : 동기화 X, Vector : 동기화 O (채팅은 동시다발적인 요청이 있으므로 동기화 처리)
    private static final List<WebSocketSession> clientList = new Vector<>();

    // 클라이언트 소켓이 서버 소켓으로부터 연결 성공 시 실행
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("클라이언트 소켓과 연동 성공 = " + session);
        // WebSocketSession : 서버로부터 요청한 클라이언트 정보가 저장된 객체
        // HttpSession : http 기반으로 클라이언트가 요청한 정보가 저장된 객체

        // 서버와 접속 성공한 클라이언트를 명단에 저장
        clientList.add(session);
    }

    // 클라이언트 소켓이 서버 소켓으로부터 연결 종료 시 실행
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("클라이언트 소켓과 연동 종료");

        // 서버와 접속 종료한 클라이언트를 명단에서 제외
        clientList.remove(session);
    }

    // 클라이언트 소켓이 서버 소켓에 메세지 전송 시 실행
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("클라이언트 소켓에서 메세지 전송");
        System.out.println("메세지 : " +message.getPayload());

        // 서버가 클라이언트한테 메세지 보내기
        // session.sendMessage(new TextMessage("응답 메세지123"));

        // 특정 클라이언트 소켓으로부터 받은 메세지를 다른 클라이언트 소켓들에게 전송하기
        for (WebSocketSession clientSocket : clientList) {
            // 명단에 저장된 클라이언트 소켓들 각각에게 메세지를 보냄
            clientSocket.sendMessage(message);
        }
    }
}
