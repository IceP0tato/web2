package 실습.exam1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import 실습.exam1.model.dto.MovieDto;
import 실습.exam1.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class MovieController {
    private final MovieService movieService;

    // 영화 등록
    @PostMapping("")
    public ResponseEntity<Boolean> postMovie(@RequestBody MovieDto movieDto) {
        boolean result = movieService.postMovie(movieDto);
        return ResponseEntity.ok(result);
    }

    // 영화 삭제
    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteMovie(@RequestParam int mno, @RequestParam String mpwd) {
        boolean result = movieService.deleteMovie(mno, mpwd);
        return ResponseEntity.ok(result);
    }

    // 영화 목록 조회
    @GetMapping("")
    public ResponseEntity<List<MovieDto>> getMovie() {
        List<MovieDto> result = movieService.getMovie();
        return ResponseEntity.ok(result);
    }

    // 특정 영화 조회
    @GetMapping("/find")
    public ResponseEntity<MovieDto> findMovie(@RequestParam int mno) {
        MovieDto result = movieService.findMovie(mno);
        return ResponseEntity.ok(result);
    }
}
