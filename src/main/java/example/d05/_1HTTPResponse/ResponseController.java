package example.d05._1HTTPResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/task/d05")
public class ResponseController {

    @GetMapping("/bool")
    public ResponseEntity<Boolean> task1() {
        return ResponseEntity.status(400).body(false);
    }

    @GetMapping("/int")
    public ResponseEntity<Integer> task2() {
        return ResponseEntity.status(202).body(1);
    }

    @GetMapping("/string")
    public ResponseEntity<String> task3() {
        return ResponseEntity.status(201).body("저장 성공 : qwe123");
    }

    @GetMapping("/void")
    public ResponseEntity<Void> task4() {
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/object")
    public ResponseEntity<Map<String, String>> task5() {
        try {
            // Integer.parseInt("a"); 강제로 예외 발생
            Map<String, String> map = new HashMap<>();
            map.put("data1", "sample");
            return ResponseEntity.status(200).body(map);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
