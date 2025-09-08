package example.d04._1Builder;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
class MemberDto {
    private String name;
    private int age;

    MemberDto() {}
    MemberDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

public class Example1 {
    public static void main(String[] args) {
        MemberDto m1 = new MemberDto();
        MemberDto m2 = new MemberDto("유재석", 40);

        // 생성자의 규칙 (유연성이 떨어짐)
        // 1. 존재하지 않는 생성자는 불가능
        // MemberDto m3 = new MemberDto("유재석");
        // 2. 매개변수의 순서를 바꿀 수 없음
        // MemberDto m4 = new MemberDto(40, "유재석");

        // Lombok 의 Builder 패턴 (생성자 유무와 관계없이 메소드로 객체 초기화)
        MemberDto m5 = MemberDto.builder().build();
        System.out.println("m5 = " + m5);
        MemberDto m6 = MemberDto.builder().name("유재석").build();
        System.out.println("m6 = " + m6);
        MemberDto m7 = MemberDto.builder().name("유재석").age(40).build();
        System.out.println("m7 = " + m7);
    }
}
