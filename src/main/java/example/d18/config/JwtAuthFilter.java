package example.d18.config;

import example.d18.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    // 개발자가 만든 토큰 인증 방법을 (시큐리티 방식) UsernamePasswordAuthenticationToken 에 혼합

    // 내가 만든 토큰 방식
    private final JwtService jwtService;

    // 기존 시큐리티 방식의 필터 커스텀 (상속: OncePerRequestFilter)
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 쿠키에서 토큰 추출
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("loginUser")) {
                    // 찾고자 하는 쿠키 (로그인 정보)가 존재할 경우 토큰 꺼내기
                    token = cookie.getValue();
                }
            }
        }
        if (token != null && jwtService.checkToken(token)) {
            String uid = jwtService.getUid(token); // 아이디 꺼내기
            String urole = jwtService.getUrole(token); // 권한 꺼내기

            // 시큐리티가 원하는 토큰 재정의
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(uid, null, List.of(
                            new SimpleGrantedAuthority("ROLE_" + urole))
                    );

            // 시큐리티 토큰 저장
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        // 다른 필터에서 해당 토큰 필터를 추가할 수 있게 설정
        filterChain.doFilter(request, response);
    }
}
