package example.d01._1SpringThread;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task/d01")
@RequiredArgsConstructor // final 변수의 생성자
public class ThreadController {
    private final ThreadService threadService;

    // Spring 매핑 컨트롤러는 자동 동기화 처리
    // 먼저 요청한 http 부터 처리한 후 반환
    @GetMapping
    public int thread1() {
        System.out.println("ThreadController.thread1");
        return threadService.thread1();
    }

    @DeleteMapping
    public void thread2() {
        System.out.println("ThreadController.thread2");
        threadService.thread2();
    }
}
