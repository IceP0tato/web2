package 실습.ex3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import 실습.ex3.model.mapper.BookMapper;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;

    @Transactional
    public boolean rentBook(Map<String, Object> data) {
        int book_id = (int)data.get("bookId");
        String member = (String)data.get("member");

        boolean bookCheck = bookMapper.rentBook(book_id);
        if (!bookCheck) throw new RuntimeException("존재하지 않는 책이거나 재고가 부족합니다.");

        boolean rentalCheck = bookMapper.addRental(book_id, member);
        if (!rentalCheck) throw new RuntimeException("로그 기록 중 오류가 발생하였습니다.");

        return true;
    }

    @Transactional
    public boolean returnBook(Map<String, Object> data) throws RuntimeException {
        int book_id = (int)data.get("bookId");
        String member = (String)data.get("member");

        boolean bookCheck = bookMapper.returnBook(book_id);
        if (!bookCheck) throw new RuntimeException("존재하지 않는 책입니다.");

        boolean rentalCheck = bookMapper.updateRental(book_id, member);
        if (!rentalCheck) throw new RuntimeException("로그 기록 중 오류가 발생하였습니다.");
        // member가 존재하지 않거나, 대출 기록이 없는 경우

        return true;
    }
}
