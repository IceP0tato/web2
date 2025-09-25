package 실습.ex3.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookMapper {
    @Update("update books set stock = stock - 1 where id = #{book_id} and stock > 0")
    boolean rentBook(int book_id);

    @Insert("insert into rentals(book_id, member) values(#{book_id}, #{member})")
    boolean addRental(int book_id, String member);

    @Update("update books set stock = stock + 1 where id = #{book_id}")
    boolean returnBook(int book_id);

    // 대출했던 순서대로 반납 처리하도록 설정
    @Update("update rentals set return_date = now() where book_id = #{book_id} and member = #{member} and return_date is null limit 1")
    boolean updateRental(int book_id, String member);
}
