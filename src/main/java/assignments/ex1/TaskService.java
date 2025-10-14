package assignments.ex1;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDao taskDao;

    // 30초마다 모든 제품의 재고 3 감소
    @Scheduled(cron = "0/30 * * * * *") // 00초와 30초에 작동
    public void task1() {
        System.out.println("TaskService.reduceProd");
        taskDao.task1();
    }

    // 1분마다 모든 제품 정보를 조회/출력
    // 0이 없으면 매 시각 1분마다 작동 (12:01:00, 13:01:00), 있으면 1분 (초가 00일 때)마다 작동 (12:57:00, 12:58:00)
    @Scheduled(cron = "0 0/1 * * * *")
    public void task2() {
        System.out.println("TaskService.task2");
        List<Map<String, Object>> data = taskDao.task2();

        System.out.println("-----------------------------------------------------------");
        for (int i=0; i<data.size(); i++) {
            Map<String, Object> obj = data.get(i);
            System.out.println("product_id = " + obj.get("product_id"));
            System.out.println("product_name = " + obj.get("product_name"));
            System.out.println("stock_quantity = " + obj.get("stock_quantity"));
            System.out.println("-----------------------------------------------------------");
        }
    }

    // 5분마다 재고가 10 이하인 상품의 재고 20 추가
    @Scheduled(cron = "0 0/5 * * * *")
    public void task3() {
        System.out.println("TaskService.task3");
        taskDao.task3();
    }
}
