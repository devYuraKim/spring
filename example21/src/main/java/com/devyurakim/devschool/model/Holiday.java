package com.devyurakim.devschool.model;

import lombok.Data;

@Data
public class Holiday {

    //final인 field는 @Data의 @RequiredArgsConstructor가 자동으로 생성해줘서 @AllArgsConstructor 하지 않아도 괜찮아
    private final String day;
    private final String reason;
    private final Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}
