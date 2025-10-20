package example.d18.service;

import example.d18.model.dto.UserDto;
import example.d18.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    // BCrypt 라이브러리 객체 주입
    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public int signup(UserDto userDto) {
        // 비밀번호 암호화 (BCrypt) [복호화는 불가능]
        userDto.setUpwd(bcrypt.encode(userDto.getUpwd())); // 1234 -> ???

        userMapper.signup(userDto);
        if (userDto.getUno() > 0) {
            return userDto.getUno();
        } else {
            return 0;
        }
    }

    public UserDto login(UserDto userDto) {
        // 입력받은 id 의 계정 존재 여부 확인
        UserDto result = userMapper.login(userDto.getUid());
        if (result == null) { return null; }

        System.out.println("[평문] 입력받은 비밀번호 : " + userDto.getUpwd());
        System.out.println("[암호문] 저장된 비밀번호 : " + result.getUpwd());

        // 계정 존재 시, 입력받은 비밀번호와 암호화된 비밀번호 비교 (평문 비교가 아니라서 암호화할 때마다 내용이 달라짐 -> .equals 사용 불가)
        // .matches(비교할 비밀번호 평문, 암호문)
        boolean result2 = bcrypt.matches(userDto.getUpwd(), result.getUpwd());
        if (result2 == true) {
            // 성공 시 반환되는 계정에는 비밀번호 제외
            result.setUpwd(null);
            return result;
        } else { return null; }
    }

    public UserDto myInfo(String uid) {
        UserDto result = userMapper.myInfo(uid);
        return result;
    }
}
