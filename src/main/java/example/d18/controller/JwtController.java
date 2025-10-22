package example.d18.controller;

import example.d18.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    @GetMapping("/create")
    public ResponseEntity<?> createToken(@RequestParam String data) {
        String token = jwtService.createToken(data);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/read")
    public ResponseEntity<?> readToken(@RequestParam String token) {
        boolean result = jwtService.readToken(token);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/value")
    public ResponseEntity<?> getValue(@RequestParam String token) {
        String value = jwtService.getValue(token);
        return ResponseEntity.ok(value);
    }
}
