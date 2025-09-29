package example.d11;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Example2 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        // stream : 데이터가 다니는 통로 (데이터(매개변수) -> 중간연산 -> 최종출력)

        // stream() + forEach()
        // 매개변수에 반복변수를 하나씩 대입하여 return 없는 반복문
        System.out.println("===================================================");
        numbers.stream()
                .forEach(x -> System.out.println("forEach : " + x));
        System.out.println("===================================================");

        // stream() + map() + collect()
        // 매개변수에 반복변수를 하나씩 대입하여 return 있는 반복문
        List<Integer> newNumbers = numbers.stream()
                .map(x -> { return x * 2; })
                .collect(Collectors.toList());
        System.out.println("new List : " + newNumbers);
        System.out.println("===================================================");

        // stream() + map() + forEach()
        numbers.stream() // 스트림 시작
                .map(x -> x * 2) // 중간 연산
                .forEach(x -> System.out.println("map + forEach : " + x)); // 최종 출력
        System.out.println("===================================================");

        // stream() + filter() + 최종출력
        numbers.stream()
                .filter(x -> x % 2 == 0)
                .forEach(x -> System.out.println("filter + forEach : " + x));
        System.out.println("===================================================");

        // stream() + sorted() + 최종출력 (정렬)
        numbers.stream()
                // .sorted() // 오름차순 (기본값)
                .sorted(Comparator.reverseOrder()) // 내림차순
                .forEach(x -> System.out.println("sorted + forEach : " + x));
        System.out.println("===================================================");

        // stream() + distinct() + 최종출력 (중복 제거)
        List<Integer> disList = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinct : " + disList);
        System.out.println("===================================================");

        // stream() + limit(N) + 최종출력 (처음부터 N개의 데이터만 제한)
        numbers.stream()
                .limit(5)
                .forEach(x -> System.out.println(x));
        System.out.println("===================================================");

        // stream() + reduce(초기값, (누적값, 현재값) -> 연산)
        int sum = numbers.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println("sum : " + sum);
        System.out.println("===================================================");

        int product = numbers.stream()
                .reduce(1, (x, y) -> x * y);
        System.out.println("product : " + product);
        System.out.println("===================================================");

        int min = numbers.stream()
                .reduce(Integer.MAX_VALUE, (x, y) -> x < y ? x : y);
        System.out.println("min : " + min);
        System.out.println("===================================================");

        int max = numbers.stream()
                .reduce(0, (x, y) -> x > y ? x : y);
        System.out.println("max : " + max);
        System.out.println("===================================================");
    }
}
