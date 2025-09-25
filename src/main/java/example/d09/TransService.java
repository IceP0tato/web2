package example.d09;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransService {
    private final TransMapper transMapper;

    @Transactional // 지정한 함수 내 모든 SQL에 트랜잭션 (모두 성공 시 commit, 하나라도 실패 시 rollback) 적용
    public boolean trans1() {
        transMapper.trans1("유재석");
        transMapper.trans2("강호동");
        return true;
    }

    @Transactional // 함수 내에서 예외(RuntimeException)가 발생하면 함수 내 SQL은 모두 최소
    public boolean transfer(Map<String, Object> transInfo) {
        String fromname = String.valueOf(transInfo.get("fromname"));
        String toname = String.valueOf(transInfo.get("toname"));
        int money = Integer.parseInt(String.valueOf(transInfo.get("money")));

        boolean result1 = transMapper.withdraw(fromname, money);
        if (result1 == false) throw new RuntimeException();

        boolean result2 = transMapper.deposit(toname, money);
        if (result2 == false) throw new RuntimeException();

        return true;
    }
}
