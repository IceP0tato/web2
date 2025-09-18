package 실습.task5.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
    private int mno;
    private String name;
    private String phone;
    private int age;
}
