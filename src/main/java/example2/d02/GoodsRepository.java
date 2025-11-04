package example2.d02;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 지정한 엔티티들을 조작하는 인터페이스
@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer> {
}
