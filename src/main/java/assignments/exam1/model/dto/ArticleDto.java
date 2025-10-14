package assignments.exam1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleDto {
    private int aNo;
    private String aTitle;
    private String aDesc;
    private String aPwd;
    private int mNo;
}
