package example.d01._1SpringThread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    public int thread1() {
        int result = 0;
        for (int i=0; i<=10; i++) {
            result += i;
            System.out.println("ThreadService.thread1");
            // 서비스 처리가 늦어질 경우 확인용 일시정지
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        // 총 10초가 지난 후 응답이 전달됨
        return result;
    }

    @Async // 응답을 먼저 하고 내부적으로 처리
    public void thread2() {
        int result = 0;
        for (int i=0; i<=10; i++) {
            result += i;
            System.out.println("ThreadService.thread2");
            // 서비스 처리가 늦어질 경우 확인용 일시정지
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        // return result;
    }
}
