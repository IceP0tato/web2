package assignments.flutter_ex1;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    public TodoDto toDto() {
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .create_date(this.getCreatedAt().toString())
                .update_date(this.getUpdatedAt().toString())
                .build();
    }
}
