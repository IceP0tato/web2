package example.d18.model.mapper;

import example.d18.model.dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into users(uid, upwd, uname, uphone, urole) values(#{uid}, #{upwd}, #{uname}, #{uphone}, #{urole})")
    @Options(useGeneratedKeys = true, keyProperty = "uno")
    int signup(UserDto userDto);

    // @Select("select * from users where uid = #{uid} and upwd = #{upwd}")
    // UserDto login(UserDto userDto);

    @Select("select * from users where uid = #{uid}")
    UserDto login(String uid);

    @Select("select uno, uid, uname, uphone, urole, udate from users where uid = #{uid}")
    UserDto myInfo(String uid);
}
