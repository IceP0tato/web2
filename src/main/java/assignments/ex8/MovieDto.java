package assignments.ex8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private int movieId;
    private String title;
    private String director;
    private String releaseDate;
    private double rating;
    private String createDate;
    private String updateDate;

    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .movieId(this.movieId)
                .title(this.title)
                .director(this.director)
                .releaseDate(this.releaseDate)
                .rating(this.rating)
                .build();
    }
}
