package assignments.ex7;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    // 도서 등록
    public BookEntity postBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    // 도서 전체 조회
    public List<BookEntity> getBook() {
        return bookRepository.findAll();
    }

    // 특정 도서 수정
    @Transactional
    public BookEntity putBook(BookEntity bookEntity) {
        Optional<BookEntity> optional = bookRepository.findById(bookEntity.getId());
        if (optional.isPresent()) {
            BookEntity entity = optional.get();
            entity.setName(bookEntity.getName());
            entity.setAuthor(bookEntity.getAuthor());
            entity.setPublisher(bookEntity.getPublisher());
            return entity;
        }
        return bookEntity;
    }

    // 특정 도서 삭제
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
