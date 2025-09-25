package example.d09;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TransMapper {
    @Insert("insert into trans(name) values(#{name})")
    boolean trans1(String name);

    @Insert("insert into trans(name) vaules(#{name})") // 오류 발생
    boolean trans2(String name);

    // 입금
    @Update("update trans set money = money + #{money} wheer name = #{name}") // 오류 발생
    boolean deposit(String name, int money);

    // 출금
    @Update("update trans set money = money - #{money} where name = #{name} and money >= #{money}")
    boolean withdraw(String name, int money);
}
