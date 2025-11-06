package example2.d03._JavaReferences;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category {
    private int cno;
    private String cname;
    @ToString.Exclude // toString 제외 (순환참조 방지)
    List<Board> boardList = new ArrayList<>();
}
