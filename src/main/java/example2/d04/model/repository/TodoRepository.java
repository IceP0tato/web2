package example2.d04.model.repository;

import example2.d04.model.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    // 쿼리 메소드 (메소드 이름으로 쿼리 생성 [카멜 표기법])
    // findBy필드명 -> select * from table where title = 매개변수
    List<TodoEntity> findByTitle(String title);
    // findBy필드명And필드명 -> select * from table where title = 매개변수 and content = 매개변수
    List<TodoEntity> findByTitleAndContent(String title, String content);
    // findBy필드명Containing -> select * from table where title like %매개변수%
    List<TodoEntity> findByTitleContaining(String keyword);
    // 페이징 처리 (Spring Pageable)
    Page<TodoEntity> findByTitleContaining(String keyword, Pageable pageable);

    // 네이티브 쿼리 메소드 (SQL 문장을 직접 작성하여 실행)
    // @Query(value = "SQL", nativeQuery = true) 추상 메소드 주입, 매개변수에 콜론(:) 사용
    @Query(value = "select * from todo where title = :title", nativeQuery = true)
    List<TodoEntity> query1(String title);
    @Query(value = "select * from todo where title = :title and content = :content", nativeQuery = true)
    List<TodoEntity> query2(String title, String content);
    @Query(value = "select * from todo where title like %:keyword%", nativeQuery = true)
    List<TodoEntity> query3(String keyword);
}
