package example2.d04.model.entity;

import example2.d04.model.dto.TodoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(columnDefinition = "longtext")
    private String content;
    @Column(columnDefinition = "boolean default false")
    private boolean done;

    public TodoDto toDto() {
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .createDate(this.getCreate_date().toString())
                .updateDate(this.getUpdate_date().toString())
                .build();
    }
}
