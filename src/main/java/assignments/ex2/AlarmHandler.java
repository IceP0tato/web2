package assignments.ex2;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Vector;

@Component
public class AlarmHandler extends TextWebSocketHandler {
    private static final List<WebSocketSession> clientList = new Vector<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        clientList.add(session);
        for (WebSocketSession clientSocket : clientList) {
            clientSocket.sendMessage(new TextMessage("익명의 유저가 접속했습니다."));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        clientList.remove(session);
        for (WebSocketSession clientSocket : clientList) {
            clientSocket.sendMessage(new TextMessage("익명의 유저가 퇴장했습니다."));
        }
    }
}
