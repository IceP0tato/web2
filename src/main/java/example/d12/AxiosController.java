package example.d12;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/axios")
public class AxiosController {

    @GetMapping("")
    public int axios1() {
        return 10;
    }

    @PostMapping("/login")
    public boolean axios2(@RequestBody Map<String, String> map, HttpSession session) {
        String id = map.get("id");
        session.setAttribute("loginId", id); // 로그인 세션 등록
        return true;
    }

    @GetMapping("/info")
    public boolean axios3(HttpSession session) {
        Object obj = session.getAttribute("loginId");
        if (obj == null) return false;
        else return true;
    }

    // form 은 @RequestParam
    @PostMapping("/form")
    public boolean axios4(@RequestParam Map<String, String> map) {
        System.out.println("map = " + map);
        return true;
    }

    @PostMapping("/formdata")
    public boolean axios5(@RequestParam MultipartFile file) {
        System.out.println("file = " + file);
        return true;
    }
}
