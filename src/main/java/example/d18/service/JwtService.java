package example.d18.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    // JWT (JSON Web Token) : 자바스크립트 기반의 특정 데이터를 대신하는 징표
    // 특정 데이터의 직접적인 표시 대신 보여주는 징표의 역할 (웹에서 데이터 숨기기[보안])

    // SHA-256 알고리즘을 사용하여 비밀키(임의, 개발자가 만듦,토큰 풀기 위한 비밀번호)를 32자 이상
    // 내가 만든 임의의 토큰에 사용되는 계산식의 비밀키
    private String secret = "1234567890123456789012345678901234567890"; // 난수 키보다는 직접 생성한 키 권장
    // 비밀키에 알고리즘 적용하여 계산식 생성
    private final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    // 토큰 생성
    public String createToken(String data) {
        // .builder() : 빌더 패턴(생성자 대신에 함수형 객체 생성) 이용한 토큰 생성
        String token = Jwts.builder()
                .claim("key", data) // .claim(key, value) : 토큰에 넣을 데이터 타입
                .setIssuedAt(new Date()) // .setIssuedAt(new Data()) : 토큰 발급 날짜(시간)
                // .setExpiration() : 토큰 만료 시간, System.currentTimeMillis() : 현재 날짜/시간을 밀리초로 변환
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 30)) // 30초
                // 알고리즘 이용한 토큰 서명 (HS256 알고리즘)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact(); // .compact() : 최종 JWT 문자열 형태로 생성
        System.out.println("token = " + token);
        return token;
    }

    // 토큰 검증 (토큰 유효 검사)
    public boolean readToken(String token) {
        try {
            Jwts.parser() // parser
                    .setSigningKey(secretKey) // 서명 검증에 필요한 비밀키 대입
                    .build() // 비밀키 확인
                    .parseClaimsJws(token); // 검증할 토큰 대입
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰 payload(내용물) claim 값 추출
    public String getValue(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody(); // 검증 후 claim(내용물) 가져오기
            System.out.println("claims = " + claims);
            String value = claims.get("key", String.class);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    // 계정 ---------------------------------------------------------------------
    // 토큰 생성 (회원 로그인 정보 전용(아이디, 권한))
    public String createToken(String uid, String urole) {
        String token = Jwts.builder()
                .claim("uid", uid) // 로그인 성공한 회원 아이디 저장
                .claim("urole", urole) // 로그인 성공한 회원 권한명 저장
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60)) // 1시간
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        System.out.println("token = " + token);
        return token;
    }

    // 토큰 검증
    public boolean checkToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // 예외 발생 시 검증 실패
        }
    }

    // 토큰 클레임(값) 추출
    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            return null;
        }
    }

    // claims 특정 값 추출
    public String getUid(String token) {
        return getClaims(token).get("uid", String.class);
    }

    public String getUrole(String token) {
        return getClaims(token).get("urole", String.class);
    }
}
