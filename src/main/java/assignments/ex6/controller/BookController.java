package assignments.ex6.controller;

import assignments.ex6.model.dto.BookDto;
import assignments.ex6.model.dto.RentalDto;
import assignments.ex6.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 대출기록 상세 뷰 생성
    @PutMapping("/view")
    public void createView() {
        bookService.createView();
    }

    // 평균 재고보다 많은 재고를 가진 도서 조회 뷰 생성
    @PutMapping("/stock")
    public void createStockView() {
        bookService.createStockView();
    }

    // 대출 상세 뷰 조회
    @GetMapping("/view")
    public ResponseEntity<?> getView() {
        List<RentalDto> result = bookService.getView();
        return ResponseEntity.ok(result);
    }

    // 평균 재고보다 많은 재고를 가진 도서 조회 뷰 조회
    @GetMapping("/stock")
    public ResponseEntity<?> getStockView() {
        List<RentalDto> result = bookService.getStockView();
        return ResponseEntity.ok(result);
    }
}
