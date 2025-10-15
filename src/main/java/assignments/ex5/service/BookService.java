package assignments.ex5.service;

import assignments.ex5.model.dto.BookDto;
import assignments.ex5.model.dto.RentalDto;
import assignments.ex5.model.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    public List<BookDto> getMoreThanAverageStock() {
        return bookMapper.getMoreThanAverageStock();
    }

    public BookDto getMostRentedBook() {
        return bookMapper.getMostRentedBook();
    }
}
