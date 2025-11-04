package assignments.ex8;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;

    // 영화 등록
    public MovieDto postMovie(MovieDto movieDto) {
        MovieEntity entity = movieDto.toEntity();
        MovieEntity result = movieRepository.save(entity);
        if (result.getMovieId() >= 0) {
            return result.toDto();
        }
        return movieDto;
    }

    // 영화 전체 조회
    public List<MovieDto> getMovie() {
        return movieRepository.findAll().stream()
                .map(MovieEntity::toDto)
                .collect(Collectors.toList());
    }

    // 영화 개별 조회
    public MovieDto findMovie(int movieId) {
        Optional<MovieEntity> optional = movieRepository.findById(movieId);
        return optional
                .map(MovieEntity::toDto)
                .orElse(null);
    }

    // 특정 영화 수정
    public MovieDto putMovie(MovieDto movieDto) {
        Optional<MovieEntity> optional = movieRepository.findById(movieDto.getMovieId());
        if (optional.isPresent()) {
            MovieEntity entity = optional.get();
            entity.setTitle(movieDto.getTitle());
            entity.setDirector(movieDto.getDirector());
            entity.setReleaseDate(movieDto.getReleaseDate());
            entity.setRating(movieDto.getRating());
            return entity.toDto();
        }
        return movieDto;
    }

    // 특정 영화 삭제
    public boolean deleteMovie(int movieId) {
        if (movieRepository.existsById(movieId)) {
            movieRepository.deleteById(movieId);
            return true;
        }
        return false;
    }
}
