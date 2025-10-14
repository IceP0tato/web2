package assignments.exam1.model.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import assignments.exam1.model.dto.ArticleDto;

import java.util.List;

@Mapper
public interface ArticleMapper {
    // 토론 글 작성
    @Insert("insert into article(aTitle, aDesc, aPwd, mNo)" +
            "values(#{aTitle}, #{aDesc}, #{aPwd}, #{mNo})")
    boolean postArticle(ArticleDto articleDto);

    // 토론 글 삭제
    @Delete("delete from article where aNo = #{aNo} and aPwd = #{aPwd}")
    boolean deleteArticle(int aNo, String aPwd);

    // 영화별 토론 전체 조회
    @Select("select * from article where mNo = #{mNo}")
    List<ArticleDto> getArticle(int mNo);

    // 특정 토론 글 조회
    @Select("select * from article where aNo = #{aNo}")
    ArticleDto findArticle(int aNo);
}
