package example.d01._2SpringScheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    // 3초마다 자동으로 실행하는 서비스 메소드
    @Scheduled(fixedRate = 3000)
    public void task1() {
        System.out.println("ScheduleService.task1");
    }

    // 5초마다 자동으로 실행하는 서비스 메소드
    @Scheduled(fixedRate = 5000) // 상수 값만 가능 (변수 불가능)
    public void task2() {
        System.out.println("ScheduleService.task2");
    }

    // (서버)시스템 날짜/시간 기준으로 자동 실행 스케줄링
    // 현재 시스템의 0:0:5초가 될 때마다 자동 실행
    @Scheduled(cron = "*/5 * * * * *")
    public void task3() {
        System.out.println("ScheduleService.task3");
    }

    // 1분마다 자동 실행
    @Scheduled(cron = "0 */1 * * * *") // (cron = 초 분 시 일 월 요일)
    public void task4() {
        System.out.println("ScheduleService.task4");
    }
}
