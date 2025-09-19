package 실습.exam1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import 실습.exam1.model.dto.MovieDto;
import 실습.exam1.model.mapper.MovieMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieMapper movieMapper;

    public boolean postMovie(MovieDto movieDto) {
        return movieMapper.postMovie(movieDto);
    }

    public boolean deleteMovie(int mNo, String mPwd) {
        return movieMapper.deleteMovie(mNo, mPwd);
    }

    public List<MovieDto> getMovie() {
        return movieMapper.getMovie();
    }

    public MovieDto findMovie(int mNo) {
        return movieMapper.findMovie(mNo);
    }
}
