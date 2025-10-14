package assignments.ex4.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import assignments.ex4.model.dto.BookDto;
import assignments.ex4.model.dto.RentalDto;

import java.util.List;

@Mapper
public interface BookMapper {
    // 책 단일 등록
    int addBook(BookDto bookDto);

    // 대출 기록 검색 (동적 쿼리)
    List<RentalDto> searchRental(String member, String title);

    // 책 일괄 등록 (동적 쿼리, 트랜잭션)
    @Transactional
    int addBooks(List<BookDto> bookDtos);
}
