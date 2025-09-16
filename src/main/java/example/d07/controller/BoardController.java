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
