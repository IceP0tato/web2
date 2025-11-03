package example2.d01;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 지정한 엔티티(테이블)을 조작하는 인터페이스 주입
// 상속 : JpaRepository<T, ID> -> <조작할 테이블, 조작할 테이블의 ID 자료형>
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {

}
