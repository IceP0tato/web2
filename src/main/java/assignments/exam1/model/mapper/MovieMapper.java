package assignments.exam1.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import assignments.exam1.model.dto.MovieDto;

import java.util.List;

@Mapper
public interface MovieMapper {
    // 영화 등록
    @Insert("insert into movie(mName, mDirector, mGenre, mDesc, mPwd)" +
            "values(#{mName}, #{mDirector}, #{mGenre}, #{mDesc}, #{mPwd})")
    boolean postMovie(MovieDto movieDto);

    // 영화 삭제
    @Delete("delete from movie where mNo = #{mNo} and mPwd = #{mPwd}")
    boolean deleteMovie(int mNo, String mPwd);

    // 영화 목록 조회
    @Select("select * from movie")
    List<MovieDto> getMovie();

    // 특정 영화 조회
    @Select("select * from movie where mNo = #{mNo}")
    MovieDto findMovie(int mNo);
}
