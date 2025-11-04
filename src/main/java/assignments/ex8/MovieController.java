package assignments.ex8;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<?> postMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.postMovie(movieDto));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getMovie() {
        return ResponseEntity.ok(movieService.getMovie());
    }

    @GetMapping
    public ResponseEntity<?> findMovie(@RequestParam int movieId) {
        return ResponseEntity.ok(movieService.findMovie(movieId));
    }

    @PutMapping
    public ResponseEntity<?> putMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.putMovie(movieDto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteMovie(@RequestParam int movieId) {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }
}
