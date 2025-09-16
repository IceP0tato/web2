package example.d07.model.mapper;

import example.d07.model.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("insert into board(bcontent, bwriter) values(#{bcontent}, #{bwriter})")
    boolean boardWrite(BoardDto boardDto);

    @Select("select * from board")
    List<BoardDto> boardPrint();

    @Select("select * from board where bno = #{bno}")
    BoardDto boardFind(int bno);

    @Delete("delete from board where bno = #{bno}")
    boolean boardDelete(int bno);

    @Update("update board set bcontent = #{bcontent} where bno = #{bno}")
    boolean boardUpdate(BoardDto boardDto);
}
