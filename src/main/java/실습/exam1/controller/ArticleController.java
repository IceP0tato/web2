package 실습.exam1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import 실습.exam1.model.dto.ArticleDto;
import 실습.exam1.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class ArticleController {
    private final ArticleService articleService;

    // 토론 글 작성
    @PostMapping("")
    public ResponseEntity<Boolean> postArticle(@RequestBody ArticleDto articleDto) {
        boolean result = articleService.postArticle(articleDto);
        return ResponseEntity.ok(result);
    }

    // 토론 글 삭제
    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteArticle(@RequestParam int ano, @RequestParam String apwd) {
        boolean result = articleService.deleteArticle(ano, apwd);
        return ResponseEntity.ok(result);
    }

    // 영화별 토론 전체 조회
    @GetMapping("")
    public ResponseEntity<List<ArticleDto>> getArticle(@RequestParam int mno) {
        List<ArticleDto> result = articleService.getArticle(mno);
        return ResponseEntity.ok(result);
    }

    // 특정 토론 글 조회
    @GetMapping("/find")
    public ResponseEntity<ArticleDto> findArticle(@RequestParam int ano) {
        ArticleDto result = articleService.findArticle(ano);
        return ResponseEntity.ok(result);
    }
}
