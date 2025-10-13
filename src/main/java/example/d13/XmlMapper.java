package example.d13;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface XmlMapper {

    // @Insert("INSERT INTO student(name, kor, math) VALUES(#{name}, #{kor}, #{math})")
    // @Options(useGeneratedKeys = true, keyProperty = "sno") // 생성된 pk 값을 sno 필드에 반환
    int save(StudentDto dto);

    List<StudentDto> findAll();

    StudentDto find(int sno);

    int delete(int sno);

    int update(StudentDto dto);

    // 동적 쿼리 (@Annotation)
    // """ """ : java15 이상부터 지원, + 연산자처럼 문자열 연결
    @Select("""
            <script>
                select * from student where 1=1
                <if test="kor != null">
                    and kor >= #{kor}
                </if>
            </script>
            """)
    StudentDto query1(int kor);

    // 동적 쿼리 (XML)
    List<StudentDto> query2(int kor);

    // 동적 쿼리 2
    List<StudentDto> query3(String name, int math);

    // 동적 쿼리 3
    int saveAll(List<StudentDto> dtos);
}
