package assignments.ex4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import assignments.ex4.model.dto.BookDto;
import assignments.ex4.model.dto.RentalDto;
import assignments.ex4.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 책 단일 등록
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
        int result = bookService.addBook(bookDto);
        int id = 0;
        if (result != 0)
            id = bookDto.getId();
        return ResponseEntity.ok(id);
    }

    // 대출 기록 검색 (동적 쿼리)
    @GetMapping("/search")
    public ResponseEntity<?> searchRental(@RequestParam String member, @RequestParam String title) {
        List<RentalDto> result = bookService.searchRental(member, title);
        return ResponseEntity.ok(result);
    }

    // 책 일괄 등록 (동적 쿼리, 트랜잭션)
    @PostMapping("/addAll")
    public ResponseEntity<?> addBooks(@RequestBody List<BookDto> bookDtos) {
        int result = bookService.addBooks(bookDtos);
        return ResponseEntity.ok(result);
    }
}
