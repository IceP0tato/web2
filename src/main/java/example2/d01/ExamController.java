package example2.d01;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ExamEntity examEntity) {
        ExamEntity saveEntity = examService.post(examEntity);
        return ResponseEntity.ok(saveEntity);
    }

    @GetMapping
    public ResponseEntity<?> get() {
        List<ExamEntity> entityList = examService.get();
        return ResponseEntity.ok(entityList);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int col1) {
        boolean result = examService.delete(col1);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody ExamEntity examEntity) {
        return ResponseEntity.ok(examService.put2(examEntity));
    }
}
