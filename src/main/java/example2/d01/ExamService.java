package example2.d01;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    // Create (등록)
    public ExamEntity post(ExamEntity examEntity) {
        // .save(저장할 엔티티) : Repository 저장 메소드, 성공 시 엔티티 반환
        ExamEntity saveEntity = examRepository.save(examEntity); // insert 자동 처리
        // 엔티티(테이블), 엔티티 객체(레코드)
        return saveEntity;
    }

    // Read (전체 조회)
    public List<ExamEntity> get() {
        // .findAll() : Repository 전체조회 메소드, 모든 엔티티 객체 반환
        List<ExamEntity> entityList = examRepository.findAll();
        return entityList;
    }

    // Update (수정, 비권장)
    public ExamEntity put(ExamEntity examEntity) {
        // .save(수정할 엔티티) : 엔티티에 pk 가 없으면 생성, 있으면 수정
        ExamEntity updateEntity = examRepository.save(examEntity);
        return updateEntity;
    }

    // Update (수정)
    // 주의 : entity 를 setter 로 변경 시, 자동으로 DB 도 변경됨
    @Transactional // 트랜잭션 (원자성 충족)
    public ExamEntity put2(ExamEntity examEntity) {
        // .findById(pk 번호) : Repository 1개 조회 메소드 (pk 번호)
        // Optional : nullPointerException 을 감싼 클래스 -> null 값에 대한 유효성 기능 제공
        Optional<ExamEntity> optional = examRepository.findById(examEntity.getCol1());
        // .isPresent() : 본문 존재 여부 검사
        if (optional.isPresent()) {
            // .get() : entity 가 존재하면 entity 꺼내기
            ExamEntity entity = optional.get();
            // setter 이용하여 entity 값 수정 (update 자동 처리)
            entity.setCol2(examEntity.getCol2());
            entity.setCol3(examEntity.getCol3());
            return entity;
        }
        return examEntity;
    }

    // Delete (삭제)
    public boolean delete(int col1) {
        // .deleteById(pk 번호);
        examRepository.deleteById(col1);
        return true;
    }
}
