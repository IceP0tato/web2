package assignments.ex7;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookEntity {
    @Id
    private int id;
    private String name;
    private String author;
    private String publisher;
}
