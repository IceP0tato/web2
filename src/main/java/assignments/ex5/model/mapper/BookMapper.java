package assignments.ex5.model.mapper;

import assignments.ex5.model.dto.BookDto;
import assignments.ex5.model.dto.RentalDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface BookMapper {
    // 평균 재고보다 많은 재고를 가진 도서 조회
    List<BookDto> getMoreThanAverageStock();

    // 가장 많이 대출한 도서 조회
    BookDto getMostRentedBook();
}
