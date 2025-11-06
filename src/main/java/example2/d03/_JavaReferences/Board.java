package example2.d03._JavaReferences;

import lombok.Data;

@Data
public class Board {
    private int bno;
    private String btitle;
    private String bcontent;
    private Category category; // FK
}
