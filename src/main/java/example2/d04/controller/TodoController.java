package example2.d04.controller;

import example2.d04.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/query1")
    public ResponseEntity<?> query1(@RequestParam String title) {
        return ResponseEntity.ok(todoService.query1(title));
    }

    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam String title, @RequestParam String content) {
        return ResponseEntity.ok(todoService.query2(title, content));
    }

    @GetMapping("/query3")
    public ResponseEntity<?> query3(@RequestParam String title) {
        return ResponseEntity.ok(todoService.query3(title));
    }

    // 페이징 처리 (조회할 페이지 번호, 조회할 자료의 총 개수)
    @GetMapping("/page")
    public ResponseEntity<?> page(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(todoService.page(page, size));
    }

    @GetMapping("/page2")
    public ResponseEntity<?> page2(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(todoService.page2(keyword, page, size));
    }
}
