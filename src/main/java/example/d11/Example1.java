package example.d11;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Calculator {
    int plus(int x, int y);
}

public class Example1 {
    public static int plus(int x, int y) { return x + y; }

    public static void main(String[] args) {
        // 일반 메소드 호출 (static)
        int result = plus(3, 5);
        System.out.println("일반 메소드 호출 : " + result);

        // 인터페이스 추상 메소드 호출 (구현체, 익명 구현체)
        // 구현체 (implements)

        // 익명 구현체
        Calculator calc = new Calculator() {
            @Override
            public int plus(int x, int y) {
                return x + y;
            }
        };
        int value1 = calc.plus(3, 5);
        System.out.println("익명 구현체 메소드 호출 : " + value1);

        // 람다식
        Calculator calc2 = (x, y) -> x + y;
        int value2 = calc2.plus(3, 5);
        System.out.println("람다식 메소드 호출 : " + value2);

        // 람다 표현식을 사용하는 *함수형* 인터페이스들
        // 제네릭 : 인스턴스(객체) 생성 시 인스턴스 내 멤버들의 타입 정의, 기본 타입 불가능
        // Functions<T, R> : T로 입력을 받아 R로 결과를 반환함, apply(T) 메소드 사용
        Function<Integer, Integer> function = x -> x * 2;
        System.out.println("Function : " + function.apply(3));

        // Supplier<T> : 입력 없이 T로 결과를 반환함, get() 메소드 사용
        Supplier<Double> supplier = () -> Math.random();
        System.out.println("Supplier : " + supplier.get());

        // Consumer<T> : T로 입력을 받아 결과 없음, accept(T) 메소드 사용
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("Consumer : 안녕하세요");

        // Predicate<T> : T로 입력을 받아 결과를 true/false 로 반환함, test(T) 메소드 사용
        Predicate<Integer> predicate = x -> x % 2 == 0; // 짝수면 true
        System.out.println("Predicate : " + predicate.test(3));
    }
}
