package example2.d04.model.dto;

import example2.d04.model.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
    private int id;
    private String title;
    private String content;
    private boolean done;
    private String createDate;
    private String updateDate;

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .build();
    }
}
