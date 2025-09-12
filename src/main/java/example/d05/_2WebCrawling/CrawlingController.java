package example.d05._2WebCrawling;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task/d05")
@RequiredArgsConstructor
public class CrawlingController {
    private final CrawlingService crawlingService;

    @GetMapping("/crawling1")
    public Map<String, String> test1() {
        return crawlingService.task1();
    }

    @GetMapping("/crawling2")
    public List<String> test2() {
        return crawlingService.task2();
    }
}
