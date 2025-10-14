package assignments.exam1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDto {
    private int mNo;
    private String mName;
    private String mDirector;
    private String mGenre;
    private String mDesc;
    private String mPwd;
}
