package example.d08;

// AOP : 관점 지향 프로그래밍
// 부가 기능을 하나로 모듈화하여 핵심 비즈니스 로직 분리
// 예) 로그, 트랜잭션, 보안 등
// implementation 'org.springframework.boot:spring-boot-starter-aop'

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@Aspect // AOP 클래스를 AOP 컨테이너에 등록
@Component  // AOP 클래스를 스프링 컨테이너에 등록
class AopClass {
    // AopService 내 모든 메소드가 실행되면 같이 실행 (경로와 조건)
    // Before : 실행 이전, After : 실행 후
    @Before("execution(* AopService.*(..))")
    public void check1() {
        System.out.println("열 체크");
    }

    @After("execution(* AopService.*(..))")
    public void check2() {
        System.out.println("퇴장");
    }

    // execution(반환타입 패키지/클래스경로.메소드명(매개변수 타입))
    @After("execution(* example.d08.AopService.enter1(..))")
    public void check3() {
        System.out.println("enter1 : AOP");
    }

    // args(매개변수 이름) : 매개변수 값들을 연결/바인딩할 이름 정의
    @Before("execution(boolean example.d08.AopService.enter3(String)) && args(name)")
    public void check4(String name) {
        System.out.println("enter3 : AOP -> " + name);
    }

    // 리턴값을 받을 수 있음 (returning = "바인딩할 변수명)
    @AfterReturning(value = "execution(boolean example.d08.AopService.enter3(..))", returning = "result")
    public void check5(boolean result) {
        System.out.println("enter3 return : " + result);
    }

    // 개발자가 직접 메소드를 실행하는 시점
    // 매개변수를 ProceedingJoinPoint 라는 비즈니스 로직과 조합
    @Around("execution(* example.d08.AopService.enter3(..))")
    public Object check6(ProceedingJoinPoint joinPoint) throws Throwable { // return 타입이 * (모두) 이므로 Object 타입으로 지정
        System.out.println(joinPoint); // 객체
        System.out.println(joinPoint.getSignature()); // 해당 AOP 메소드를 실행한 대상(메소드)
        System.out.println(Arrays.toString(joinPoint.getArgs())); // 실행한 대상(메소드) 매개변수의 인자들(배열)
        Object result = joinPoint.proceed(); // 실행할 대상 메소드를 직접 실행 (실행 시점 커스텀)
        System.out.println(result);
        return result; // 실행한 대상 메소드의 리턴값을 그대로 리턴해야 함
    }
}

@Service
class AopService {
    public void enter1() {
        System.out.println("학원 입장");
    }

    public void enter2() {
        System.out.println("식당 입장");
    }

    public boolean enter3(String name) {
        System.out.println("헬스장 입장");
        return true; // 임의 데이터
    }
}

@RestController
class AopController {
    @Autowired AopService aopService;

    @GetMapping ("/d08/aop")
    public void method() {
        System.out.println("===========================");
        aopService.enter1();
        System.out.println("===========================");
        aopService.enter2();
        System.out.println("===========================");
        aopService.enter3("유재석");
        System.out.println("===========================");
    }
}

@SpringBootApplication
public class Example2 {
    public static void main(String[] args) {
        SpringApplication.run(Example2.class);
    }
}
