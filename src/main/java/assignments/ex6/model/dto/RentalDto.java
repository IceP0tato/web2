package assignments.ex6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentalDto {
    private int id;
    private int book_id;
    private String member;
    private String rent_date;
    private String return_date;
    private String title;
    private int stock;
}
