package assignments.ex6.model.mapper;

import assignments.ex6.model.dto.BookDto;
import assignments.ex6.model.dto.RentalDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    // 대출기록 상세 뷰 생성
    void createView();

    // 평균 재고보다 많은 재고를 가진 도서 조회 뷰 생성
    void createStockView();

    // 대출 상세 뷰 조회
    List<RentalDto> getView();

    // 평균 재고보다 많은 재고를 가진 도서 조회 뷰 조회
    List<RentalDto> getStockView();
}
