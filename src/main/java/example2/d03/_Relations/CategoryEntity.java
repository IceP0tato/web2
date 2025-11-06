package example2.d03._Relations;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "ecategory")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private String cname;
    @OneToMany(mappedBy = "categoryEntity") // 1:n 관계
    @ToString.Exclude // 순환참조 방지
    @Builder.Default // 빌더 패턴 초기값 사용
    private List<BoardEntity> boardEntityList = new ArrayList<>(); // 양방향 연결
}
