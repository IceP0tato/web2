package example2.d02;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 여러 엔티티들의 생성/수정 (날짜/시간) 자동 주입
@Getter
@MappedSuperclass // 엔티티 상속 용도
@EntityListeners(AuditingEntityListener.class) // 해당 엔티티 자동 감시 적용
public class BaseTime {
    @CreatedDate // 현재 날짜/시간을 자동 주입
    private LocalDateTime create_date;

    @LastModifiedDate // 엔티티 변화 시점의 날짜/시간 자동 주입
    private LocalDateTime update_date;
}
