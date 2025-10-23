package example.d18.config;

import example.d18.service.JwtService;
import example.d18.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 타사 로그인 성공 이후 로직 커스텀 (실패 없음)
        // 로그인 성공한 회원의 타사 발급한 토큰 확인 (OAuth2 라이브러리)
        System.out.println(authentication); // authentication 인증 (토큰, 개인정보 등)
        // 로그인 성공한 토큰 확인, OAuth2AuthenticationToken : 타사의 회사명
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        System.out.println("authToken = " + authToken);
        // 로그인 성공한 회원의 동의 항목, Oauth2User
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); // Principal() 주체 (로그인 성공한 정보)
        System.out.println("oAuth2User = " + oAuth2User);

        // google / kakao 식별 (회사마다 동의 항목이 다르기에 식별이 필요함)
        String provider = authToken.getAuthorizedClientRegistrationId(); // 등록된 공급자 ID (google, kakao 등)
        System.out.println("provider = " + provider);
        // 동의 항목 정보 (oAuth2User.getAttribute("동의 항목명"))
        String uid = null;
        String name = null;
        if (provider.equals("google")) {
            uid = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
        } else if (provider.equals("kakao")) {
            // 카카오의 동의 항목(정보) : profile_nickname, kakao_account
            Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            uid = (String)profile.get("nickname"); // 권한이 없으므로 임의값 지정
            name = (String)profile.get("nickname");
        }

        // oauth2 정보를 데이터베이스 저장
        userService.oauth2UserSignup(uid, name);

        // 자사의 로그인 방식 통합
        Cookie cookie = new Cookie("loginUser", jwtService.createToken(uid, "USER"));
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        // 로그인 성공 시 이동 (FrontEnd)
        response.sendRedirect("/"); // 자바 서버 메인 경로 (localhost:8080)
    }
}
