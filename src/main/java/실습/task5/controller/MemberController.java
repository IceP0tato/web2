package 실습.task5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import 실습.task5.model.dto.MemberDto;
import 실습.task5.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/task5")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:5173")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<Boolean> addMember(@RequestBody MemberDto memberDto) {
        boolean result = memberService.addMember(memberDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    public ResponseEntity<List<MemberDto>> printMember() {
        List<MemberDto> result = memberService.printMember();
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/find")
//    public ResponseEntity<MemberDto> findMember(@RequestParam int mno) {
//        MemberDto result = memberService.findMember(mno);
//        return ResponseEntity.ok(result);
//    }

//    @PutMapping("")
//    public ResponseEntity<Boolean> updateMember(@RequestBody MemberDto memberDto) {
//        boolean result = memberService.updateMember(memberDto);
//        return ResponseEntity.ok(result);
//    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteMember(@RequestParam int mno) {
        boolean result = memberService.deleteMember(mno);
        return ResponseEntity.ok(result);
    }
}
