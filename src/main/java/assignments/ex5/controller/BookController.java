package assignments.ex5.controller;

import assignments.ex5.model.dto.BookDto;
import assignments.ex5.model.dto.RentalDto;
import assignments.ex5.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/stock")
    public ResponseEntity<?> getMoreThanAverageStock() {
        List<BookDto> result = bookService.getMoreThanAverageStock();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/book")
    public ResponseEntity<?> getMostRentedBook() {
        BookDto result = bookService.getMostRentedBook();
        return ResponseEntity.ok(result);
    }
}
