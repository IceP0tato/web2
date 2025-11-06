package assignments.ex9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity // 시큐리티 설정 어노테이션
@EnableJpaAuditing // 데이터베이스 모니터링 작동
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}