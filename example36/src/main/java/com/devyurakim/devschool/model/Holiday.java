package com.devyurakim.devschool.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity {

    //final인 field는 @Data의 @RequiredArgsConstructor가 자동으로 생성해줘서 @AllArgsConstructor 하지 않아도 괜찮아
    //private final String day;
    //private final String reason;
    //private final Type type;

    @Id
    private String day;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}
