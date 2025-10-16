package assignments.ex6.service;

import assignments.ex6.model.dto.BookDto;
import assignments.ex6.model.dto.RentalDto;
import assignments.ex6.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    // 대출기록 상세 뷰 생성
    public void createView() {
        bookMapper.createView();
    }

    // 평균 재고보다 많은 재고를 가진 도서 조회 뷰 생성
    public void createStockView() {
        bookMapper.createStockView();
    }

    // 대출 상세 뷰 조회
    public List<RentalDto> getView() {
        return bookMapper.getView();
    }

    // 평균 재고보다 많은 재고를 가진 도서 조회 뷰 조회
    public List<RentalDto> getStockView() {
        return bookMapper.getStockView();
    }
}
