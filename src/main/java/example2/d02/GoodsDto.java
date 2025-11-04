package example2.d02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 엔티티는 서비스에서만 사용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDto {
    // 데이터베이스/엔티티 필드/속성 기반으로 구성
    private int gno;
    private int gprice;
    private String create_date;
    private String update_date;
    private String gname;
    private String gdesc;

    // DTO -> Entity (Controller -> Service) - 등록, 수정
    public GoodsEntity toEntity() {
        return GoodsEntity.builder()
                .gno(this.gno)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                // .date 자동 제외
                .build();
    }
}
