package example.d10.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Log4j2
public class LogController {
    // 로그 함수 사용
    @GetMapping
    public void log() {
        // log.XXXX : println 처럼 출력하면서 부가기능(파일처리/제약조건 등) 제공
        log.debug("테스트 과정 중 사용"); // 테스트 단계 (properties 설정 필요)
        log.info("서비스 흐름과 데이터 상태에서 사용"); // 운용 단계
        log.warn("잠재적 문제일 때 사용"); // 유지보수 단계
        log.error("예외/오류 처리 시 사용"); // 운용/유지보수 단계
    }
}
