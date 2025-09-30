package example.d12;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정 관련 빈 등록
public class CorsConfig implements WebMvcConfigurer {
    // SpringWeb MVC 설정 재구현

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/axios/**") // 허용할 컨트롤러 URL (전체 : /**)
                .allowedOrigins("http://localhost:5173", "http://localhost:5174") // 허용할 출처/도메인 (전체 : *)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드 (전체 : *)
                .allowCredentials(true) // HTTP 인증(세션 유지) 허용
                .allowedHeaders("*"); // HTTP 헤더 정보 허용
    }
}
