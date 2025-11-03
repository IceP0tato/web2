package example2.d01;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Entity 주입
@Data
public class ExamEntity {
    @Id // primary key
    private int col1;
    private String col2;
    private double col3;
}
