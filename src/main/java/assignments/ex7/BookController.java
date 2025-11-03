package assignments.ex7;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody BookEntity bookEntity) {
        return ResponseEntity.ok(bookService.postBook(bookEntity));
    }

    @GetMapping
    public ResponseEntity<?> getBook() {
        return ResponseEntity.ok(bookService.getBook());
    }

    @PutMapping
    public ResponseEntity<?> putBook(@RequestBody BookEntity bookEntity) {
        return ResponseEntity.ok(bookService.putBook(bookEntity));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestParam int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(true);
    }
}
