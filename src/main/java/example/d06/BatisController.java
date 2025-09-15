package example.d06;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/d06/batis")
@RequiredArgsConstructor
public class BatisController {
    private final BatisMapper batisMapper;

    @PostMapping("")
    public ResponseEntity<Integer> save(@RequestBody StudentDto studentDto) {
        int result = batisMapper.save(studentDto);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> result = batisMapper.findAll();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/find")
    public ResponseEntity<Map<String, Object>> find(@RequestParam int sno) {
        Map<String, Object> result = batisMapper.find(sno);
        return ResponseEntity.status(200).body(result);
    }

    @DeleteMapping("")
    public ResponseEntity<Integer> delete(@RequestParam int sno) {
        int result = batisMapper.delete(sno);
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("")
    public ResponseEntity<Integer> update(@RequestBody StudentDto studentDto) {
        int result = batisMapper.update(studentDto);
        return ResponseEntity.ok(result);
    }
}
