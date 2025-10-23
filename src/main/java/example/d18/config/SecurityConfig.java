package example.d18.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    // 스프링 시큐리티 라이브러리 커스텀

    // 내가 만든 토큰을 시큐리티 토큰 방식으로 통합한 클래스
    private final JwtAuthFilter jwtAuthFilter;

    private final Oauth2SuccessHandler oauth2SuccessHandler;

    // 시큐리티(보안) 필터(검증/확인) 체인
    // HTTP 관련 필터 커스텀
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // HTTP 요청에 따른 권한 커스텀 (.permitAll : 모든 권한 (공개용) 허용)
                // .hasRole("권한명", .hasAnyRole("권한명", "권한명");
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/myInfo").hasAnyRole("USER", "ADMIN") // 권한명은 대문자 권장
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // admin 권한만 접근 가능
                        .requestMatchers("/**").permitAll()); // 모든 권한은 항상 최하단에 정의해야 함

        // HTTP 요청에 csrf(요청 간의 해킹 공격) post, put 자동 차단 커스텀
        // http.csrf(csrf -> csrf.ignoringRequestMatchers("csrf 제외할 경로"));

        // csrf 사용 안함 (개발 단계 권장)
        http.csrf(csrf -> csrf.disable());

        // 시큐리티 내부에서 사용되는 토큰 (UsernamePasswordAuthenticationToken)
        // 실습에서는 쿠키 기반의 토큰 구현 = 시큐리티가 제공하는 세션 사용 안함
        // 시큐리티 세션 끄기
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 개발자가 만든 토큰으로 대체
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // OAuth2 로그인 필터 사용 설정 (http.oauth2Login(param -> param.successHandler(로그인 성공 시 특정 클래스 이동)))
        http.oauth2Login(o -> o
                .loginPage("/oauth2/authorization/google") // 현재 서버의 로그인 페이지가 아닌 타사 로그인 페이지 사용
                .successHandler(oauth2SuccessHandler) // 타사 로그인 페이지에서 로그인 성공 시 반환되는 클래스 정의
        );

        return http.build(); // 커스텀 완료된 객체 반환
    }
}
