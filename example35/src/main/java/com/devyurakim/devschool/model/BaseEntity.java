package com.devyurakim.devschool.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass //다른 entity에 공통적으로 사용되지만 검색이 필요 없는 column을 정의하는 경우
public class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;
}