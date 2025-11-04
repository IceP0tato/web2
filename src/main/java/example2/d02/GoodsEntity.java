package example2.d02;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 해당 클래스를 데이터베이스 테이블과 매핑
@Table(name = "goods") // 테이블 이름 정의, 생략 시 클래스 명으로 정의
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsEntity extends BaseTime {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int gno;

    @Column(nullable = false, length = 100) // null 제외, 글자수 100
    private String gname;

    @Column(nullable = true) // null 포함
    private int gprice;

    @Column(columnDefinition = "varchar(100) default '제품설명' not null") // SQL 구문 직접 작성
    private String gdesc;

    // Entity -> Dto (Service -> Controller) - 출력
    public GoodsDto toDto() {
        // 객체 생성 방법1: new 클래스명(value, value)
        // 객체 생성 방법2: 클래스명.builder().key(value).key(value).build()
        return GoodsDto.builder()
                .gno(this.gno)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                .update_date(this.getUpdate_date().toString())
                .create_date(this.getCreate_date().toString())
                .build();
    }
}
