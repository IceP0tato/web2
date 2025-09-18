package 실습.task5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import 실습.task5.model.dto.MemberDto;
import 실습.task5.model.mapper.MemberMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    public boolean addMember(MemberDto memberDto) {
        return memberMapper.addMember(memberDto);
    }

    public List<MemberDto> printMember() {
        return memberMapper.printMember();
    }

//    public MemberDto findMember(int mno) {
//        return memberMapper.findMember(mno);
//    }

//    public boolean updateMember(MemberDto memberDto) {
//        return memberMapper.updateMember(memberDto);
//    }

    public boolean deleteMember(int mno) {
        return memberMapper.deleteMember(mno);
    }
}
