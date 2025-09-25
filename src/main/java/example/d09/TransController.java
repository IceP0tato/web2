package example.d09;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/d09/trans")
@RequiredArgsConstructor
public class TransController {
    private final TransService transService;

    @PostMapping
    public boolean trans1() {
        return transService.trans1();
    }

    @PostMapping("/transfer")
    public boolean transfer(@RequestBody Map<String, Object> transInfo) {
        return transService.transfer(transInfo);
    }

}
