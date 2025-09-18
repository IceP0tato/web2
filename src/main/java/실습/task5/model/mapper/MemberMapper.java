package 실습.task5.model.mapper;

import org.apache.ibatis.annotations.*;
import 실습.task5.model.dto.MemberDto;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Insert("insert into member(name, phone, age) values(#{name}, #{phone}, #{age})")
    boolean addMember(MemberDto memberDto);

    @Select("select * from member")
    List<MemberDto> printMember();

//    @Select("select * from member where mno = #{mno}")
//    MemberDto findMember(int mno);

//    @Update("update member set name = #{name}, phone = #{phone}, age = ${age} where mno = #{mno}")
//    boolean updateMember(MemberDto memberDto);

    @Delete("delete from member where mno = #{mno}")
    boolean deleteMember(int mno);
}
