package assignments.ex4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import assignments.ex4.model.dto.BookDto;
import assignments.ex4.model.dto.RentalDto;
import assignments.ex4.model.mapper.BookMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    public int addBook(BookDto bookDto) {
        return bookMapper.addBook(bookDto);
    }

    public List<RentalDto> searchRental(String member, String title) {
        return bookMapper.searchRental(member, title);
    }

    public int addBooks(List<BookDto> bookDtos) {
        return bookMapper.addBooks(bookDtos);
    }
}
