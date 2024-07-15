package com.devyurakim.devschool.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
/*도대체 어떤 시간과 어떤 주체로 아래 정보들을 대체할 것인가?
* >> 시간: DB 서버 시간
* >> 주체: AuditAwareImpl에서 정의한 주체 */

@Data
@MappedSuperclass //다른 entity에 공통적으로 사용되지만 검색이 필요 없는 column을 정의하는 경우
@EntityListeners(AuditingEntityListener.class) //해당 class에 auditing 기능 부여
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) //update할 때는 해당 column을 수정 불가하도록 설정
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false) //새로 추가(insert)할 때는 해당 column을 입력하지 못하도록 설정
    private LocalDateTime updatedAt;
    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}