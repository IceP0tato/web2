package 실습.ex3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import 실습.ex3.service.BookService;

import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/rent")
    public boolean rentBook(@RequestBody Map<String, Object> data) {
        return bookService.rentBook(data);
    }

    @PostMapping("/return")
    public boolean returnBook(@RequestBody Map<String, Object> data) {
        return bookService.returnBook(data);
    }
}
