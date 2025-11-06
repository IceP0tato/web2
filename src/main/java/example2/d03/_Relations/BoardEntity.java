package example2.d03._Relations;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "eboard")
@Data
@Builder
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    private String btitle;
    private String bcontent;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // n:1 관계
    @JoinColumn(name = "cno") // FK 필드명 (PK 필드명과 동일하게 하는 것이 권장됨)
    private CategoryEntity categoryEntity; // 단방향 연결
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();
}

/*
영속성 : 자바 데이터(객체)와 데이터베이스 데이터(테이블/레코드)를 매핑(연결)
    -> 영속중일 시 자바 객체 수정 시 DB 데이터도 수정됨
    -> 영속중이 아닐 시 자바 객체 수정 시 DB 데이터는 변화 없음

[cascade] (PK-FK 제약 조건 옵션)
CascadeType.ALL : 부모가 변경될 시 자식도 같이 변경 - 권장
CascadeType.PERSIST : 부모가 저장될 시 자식도 같이 저장
CascadeType.MERGE : 부모가 수정될 시 자식도 같이 수정
CascadeType.REMOVE : 부모가 삭제될 시 자식도 같이 삭제
CascadeType.REFRESH : 부모가 재호출될 시 자식도 같이 재호출(갱신)
CascadeType.DETACH : 부모의 영속이 해제될 시 자식도 영속 해제

[fetch] (PK-FK 관계에서 엔티티 조회 방법)
FetchType.EAGER : 해당 엔티티를 조회하면 참조 엔티티도 즉시 조회
    (기본값, 초기 로딩 느림, 불필요한 엔티티 정보가 있을 경우 기능 저하)
FetchType.LAZY : 해당 엔티티를 조회하면 참조 엔티티를 조회하지 않음 - 권장
    (초기 로딩 빠름, 사용할 엔티티 정보를 적절하게 사용하면 성능 최적화, 지연 로딩)

MyBatis 와 JPA 는 SELECT 된 결과를 자동 1차 캐싱함 (결과가 동일할 경우)

*/