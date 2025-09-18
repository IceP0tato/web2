package example.d07.controller;

import example.d07.model.dto.BoardDto;
import example.d07.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@CrossOrigin(value="http://localhost:5173") // CORS (서로 다른 서버 간의 요청) 정책 설정
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<Boolean> boardWrite(@RequestBody BoardDto boardDto) {
        boolean result = boardService.boardWrite(boardDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("")
    public ResponseEntity<List<BoardDto>> boardPrint() {
        List<BoardDto> result = boardService.boardPrint();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/find")
    public ResponseEntity<BoardDto> boardFind(@RequestParam int bno) {
        BoardDto result = boardService.boardFind(bno);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> boardDelete(@RequestParam int bno) {
        boolean result = boardService.boardDelete(bno);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("")
    public ResponseEntity<Boolean> boardUpdate(@RequestBody BoardDto boardDto) {
        boolean result = boardService.boardUpdate(boardDto);
        return ResponseEntity.ok().body(result);
    }
}
