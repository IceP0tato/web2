package example.d18.controller;

import example.d18.model.dto.UserDto;
import example.d18.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) {
        int result = userService.signup(userDto);
        return ResponseEntity.ok(result);
    }

    /*
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session) {
        UserDto result = userService.login(userDto);
        if (result != null) {
            // 로그인 성공 시 해당 유저의 아이디를 세션 (자바 웹 '서버'(Tomcat) 의 임시 저장소) 에 저장
            session.setAttribute("loginUser", result.getUid());
        }
        return ResponseEntity.ok(result);
    }
    */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {
        UserDto result = userService.login(userDto);
        if (result != null) {
            // 로그인 성공 시 해당 유저의 아이디를 쿠키 ('클라이언트' 브라우저의 임시 저장소) 에 저장
            Cookie cookie = new Cookie("loginUser", result.getUid());

            // 쿠키 노출(탈취) 방지 :  민감한 정보에서 사용
            // .setHttpOnly(true) : 무조건 http 에서만 사용 -> js 로 접근 불가
            cookie.setHttpOnly(true);
            // 암호화 처리 (http 탈취 방지, 단 https 에서만 가능 (true))
            cookie.setSecure(false);

            // 쿠키 접근 경로
            cookie.setPath("/");
            // 쿠키 유효 기간 (초)
            cookie.setMaxAge(60 * 60);
            // 생성한 쿠키를 클라이언트에게 반환
            response.addCookie(cookie);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<?> myInfo(HttpServletRequest request) {
        // 클라이언트에 저장된 모든 쿠키 가져오기
        Cookie[] cookies = request.getCookies();
        // 특정 쿠키명 찾기
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("loginUser")) {
                    // "loginUser" 와 일치할 시, 저장된 값 반환
                    String uid = c.getValue();
                    UserDto result = userService.myInfo(uid);
                    // 로그인 상태
                    return ResponseEntity.ok(result);
                }
            }
        }
        // 비로그인 상태
        return ResponseEntity.ok(null);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // 삭제할 쿠키명을 null 값으로 변경
        Cookie cookie = new Cookie("loginUser", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        // 즉시 삭제
        cookie.setMaxAge(0);
        // 동일한 쿠키명으로 null 저장 (기존 쿠키명 사라짐)
        response.addCookie(cookie);

        return ResponseEntity.ok(true);
    }
}
