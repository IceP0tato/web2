package example.d13;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XmlController {
    private final XmlMapper xmlMapper;

    @PostMapping("")
    // <?> : 모든 타입
    public ResponseEntity<?> save(@RequestBody StudentDto dto) {
        System.out.println("dto = " + dto); // save 이전
        int result = xmlMapper.save(dto);
        System.out.println("dto = " + dto); // save 이후 (sno 값이 추가됨)
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<StudentDto> result = xmlMapper.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/find")
    public ResponseEntity<?> find(@RequestParam int sno) {
        StudentDto result = xmlMapper.find(sno);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam int sno) {
        int result = xmlMapper.delete(sno);
        return ResponseEntity.ok(result);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody StudentDto dto) {
        int result = xmlMapper.update(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam int kor) {
        List<StudentDto> result = xmlMapper.query2(kor);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/query3")
    public ResponseEntity<?> query3(@RequestParam String name, @RequestParam int math) {
        List<StudentDto> result = xmlMapper.query3(name, math);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAll(@RequestBody List<StudentDto> dtos) {
        int result = xmlMapper.saveAll(dtos);
        return ResponseEntity.ok(result);
    }
}
