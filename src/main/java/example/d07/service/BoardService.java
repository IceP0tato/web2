package example.d07.service;

import example.d07.model.dto.BoardDto;
import example.d07.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public boolean boardWrite(BoardDto boardDto) {
        return boardMapper.boardWrite(boardDto);
    }

    public List<BoardDto> boardPrint() {
        return boardMapper.boardPrint();
    }

    public BoardDto boardFind(int bno) {
        return boardMapper.boardFind(bno);
    }

    public boolean boardDelete(int bno) {
        return boardMapper.boardDelete(bno);
    }

    public boolean boardUpdate(BoardDto boardDto) {
        return boardMapper.boardUpdate(boardDto);
    }
}
