package example.d11;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example3 {
    public static void main(String[] args) {
        // 메소드 레퍼런스 : 이미 정의된 메소드를 참조해서 사용하는 표현식 (::)

        System.out.println(Integer.parseInt("123"));

        Function<String, Integer> func = Integer::parseInt;
        System.out.println(func.apply("123"));

        List<String> names = List.of("유재석", "강호동", "신동엽");
        names.stream().forEach(System.out::println);

        // 생성자
        names.stream().forEach(Member::new);

        List<Member> newMember = names.stream()
                .map(Member::new)
                .collect(Collectors.toList());
        System.out.println("newMember = " + newMember);
    }
}

class Member {
    String name;
    Member(String name) {this.name = name;}
}