package example.d01._1SpringThread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration // 스프링 컨테이너 빈 등록 (IOC)
public class ThreadPoolConfig {

    // 스프링 멀티스레드 커스텀
    @Bean // 해당 메소드를 컨테이너 빈 등록
    public Executor taskExecutor() {
        // 스레드풀 작업스레드 객체
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); // 기본(최소) 실행 스레드 개수 설정
        executor.setMaxPoolSize(10); // 최대 실행 스레드 개수 설정
        executor.setQueueCapacity(20); // 최대 대기 개수 설정 (초과 시 503 오류)
        executor.initialize(); // 스레드풀 초기화 (서버 재실행마다 초기화)
        return executor;
    }
}
