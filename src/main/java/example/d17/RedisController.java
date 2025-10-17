package example.d17;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {
    // redis 접근 객체
    private final RedisTemplate redisTemplate;
    private final RedisTemplate<String, Object> dtoRedisTemplate;

    // 텍스트를 redis 서버에 저장 및 호출
    @GetMapping("/test")
    public ResponseEntity<?> test1() {
        // 저장 : opsForValue().set(key, value);
        redisTemplate.opsForValue().set("유재석", "90");
        redisTemplate.opsForValue().set("강호동", "80");
        redisTemplate.opsForValue().set("유재석", "100"); // key는 중복을 허용하지 않음 (덮어쓰기)

        // 호출 : keys("*"); -> 저장된 모든 키 반환
        Set<String> keys = redisTemplate.keys("*");
        List<Object> result = new ArrayList<>();
        // 특정 키의 값 호출 : opsForValue().get(key);
        for (String key : keys) {
            result.add(redisTemplate.opsForValue().get(key));
        }

        return ResponseEntity.ok(result);
    }

    // 데이터베이스 없이 CRUD 구현
    private final RedisTemplate<String, Object> studentTemplate;

    // 등록
    @PostMapping("")
    private ResponseEntity<?> save(@RequestBody StudentDto studentDto) {
        // 중복 없는 key 구상
        String key = "student:"+studentDto.getSno(); // sno를 key로 치환 -> student:1, student:2
        // redis 에 전달받은 값 저장 (ex: {"student:1" : {sno:1, name:"강호동", math:80, kor:90}})
        studentTemplate.opsForValue().set(key, studentDto);
        return ResponseEntity.ok().body("저장 성공");
    }

    // 전체 조회
    @GetMapping("")
    private ResponseEntity<?> findAll() {
        // 조회할 key 모두 가져오기 (* - 모든 키, xxxx:* - 동일한 * 자리는 임의의 문자 대응)
        Set<String> keys = studentTemplate.keys("student:*"); // 문자열까지 동일하면 * 위치는 서로 다른 문자열 패턴
        // 반복문을 이용하여 value 를 list 에 담기
        List<Object> result = new ArrayList<>();
        for (String key : keys) {
            result.add(studentTemplate.opsForValue().get(key));
        }
        return ResponseEntity.ok().body(result);
    }

    // 개별 조회
    @GetMapping("/find")
    private ResponseEntity<?> find(@RequestParam int sno) {
        // 조회할 key 구상
        String key = "student:"+sno;
        // 특정 key 의 value 호출
        Object result = studentTemplate.opsForValue().get(key);
        return ResponseEntity.ok(result);
    }

    // 개별 삭제
    @DeleteMapping("")
    private ResponseEntity<?> delete(@RequestParam int sno) {
        // 삭제할 key 구상
        String key = "student:"+sno;
        // 특정 key 를 이용한 엔트리(key+value 한 쌍) 삭제 : delete(key);
        boolean result = studentTemplate.delete(key);
        return ResponseEntity.ok(result);
    }

    // 개별 수정
    @PutMapping("")
    private ResponseEntity<?> update(@RequestBody StudentDto studentDto) {
        // 수정할 key 구상
        String key = "student:"+studentDto.getSno();
        // 특정한 key 덮어쓰기
        studentTemplate.opsForValue().set(key, studentDto);
        return ResponseEntity.ok(true);
    }

    // http://localhost:8080/redis/auth/send?phone=01012341234
    // 인증코드 발급해서 redis 유효기간 정하기 (TTL : redis 에 저장된 엔트리를 특정 기간이 지나면 자동 삭제)
    @GetMapping("/auth/send")
    public ResponseEntity<?> authSend(@RequestParam String phone) {
        // key 구상 ("auth:전화번호")
        String key = "auth:"+phone;
        // 인증 코드 생성 (난수 6자리)
        String code = String.format("%06d", new Random().nextInt(999999));
        // 인증 코드 저장, TTL(유효기간)
        redisTemplate.opsForValue().set(key, code, Duration.ofSeconds(10)); // 유효 기간 10초
        // API 이용하여 전화번호로 인증 코드 전송 (concept)
        return ResponseEntity.ok().body("인증 코드 발급 완료 : " + code);
    }

    // http://localhost:8080/redis/auth/confirm?phone=01012341234&code=365820
    @GetMapping("/auth/confirm")
    public ResponseEntity<?> authConfirm(@RequestParam String phone, @RequestParam String code) {
        // 조회할 key 구상
        String key = "auth:"+phone;
        // value 호출
        Object savedCode = redisTemplate.opsForValue().get(key);
        if (savedCode == null) { return ResponseEntity.ok("인증 만료"); }
        else if (savedCode.equals(code)) { return ResponseEntity.ok("인증 성공"); }
        else return ResponseEntity.ok("인증 실패");
    }

}
