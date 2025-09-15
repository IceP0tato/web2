package example.d06;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BatisMapper {

    @Insert("insert into student(name, kor, math) values(#{name}, #{kor}, #{math})")
    int save(StudentDto studentDto);

    @Select("select * from student")
    List<StudentDto> findAll();

    @Select("select * from student where sno = #{sno}")
    Map<String, Object> find(int sno);

    @Delete("delete from student where sno = #{sno}")
    int delete(int sno);

    @Update("update student set kor = #{kor}, math = #{math} where sno = #{sno}")
    int update(StudentDto studentDto);
}
