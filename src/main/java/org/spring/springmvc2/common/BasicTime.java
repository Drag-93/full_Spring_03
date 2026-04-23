package org.spring.springmvc2.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BasicTime {
    //날짜시간 필수
    @CreationTimestamp // 처음 생성시 현재시간 저장
    @Column(updatable = false) // 업데이트시 저장 X -> 처음 생성시 한번만 저장됨
    private LocalDateTime createTime;

    @UpdateTimestamp // 수정 시 마다 현재시간 저장
    @Column(insertable = false) // 처음 생성 시 저장X -> 업데이트 시에만 저장됨
    private LocalDateTime updateTime;
}
