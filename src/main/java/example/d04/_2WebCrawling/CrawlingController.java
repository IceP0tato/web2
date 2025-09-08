package example.d04._2WebCrawling;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task/d04")
@RequiredArgsConstructor
public class CrawlingController {
    private final CrawlingService crawlingService;

    @GetMapping("/craw1")
    public List<String> task1() {
        return crawlingService.task1();
    }

    @GetMapping("/craw2")
    public List<Map<String, String>> task2() {
        return crawlingService.task2();
    }

    @GetMapping("/craw3")
    public Map<String, String> task3() {
        return crawlingService.task3();
    }
}
