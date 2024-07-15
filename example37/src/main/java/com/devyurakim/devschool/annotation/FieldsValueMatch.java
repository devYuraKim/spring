package com.devyurakim.devschool.annotation;

import com.devyurakim.devschool.validations.FieldsValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    Class<?>[] groups() default {}; //그룹은 사용 시에 annotation 내부에서 설정
    Class<? extends Payload>[] payload() default {}; //payload(메타데이터)는 사용 시에 annotation 내부에서 설정

    String message() default "필드 입력값이 일치하지 않음!";

    /*두 개의 필드 입력값에 대해 비교를 실시할 것이므로 아래 코드가 필요함*/
    String field();
    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
