package example.d02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// ws 프로토콜 통신이 왔을 때 특정한 핸들러로 매핑
@Configuration // 스프링 컨테이너에 빈 등록
@EnableWebSocket // 웹 소켓 사용 활성화
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private ChatHandler chatHandler;

    // 개발자가 만든 서버 웹 소켓 객체(핸들러)들을 스프링이 알 수 있게 경로 등록
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 내가 만든 서버 웹 소켓 주소와 함께 등록
        // addHandler(서버 웹 소켓 객체, "경로");
        registry.addHandler(chatHandler, "/chat");
    }
}
