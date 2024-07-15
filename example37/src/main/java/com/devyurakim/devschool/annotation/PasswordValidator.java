package com.devyurakim.devschool.annotation;

import com.devyurakim.devschool.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented //Javadoc 사용 시에 생성된 documentation에 해당 annotation 포함
@Constraint(validatedBy = PasswordStrengthValidator.class) //custom logic이 포함된 class 명시
@Target( { ElementType.METHOD, ElementType.FIELD }) //해당 annotation이 무엇을 대상으로 실행되는지
@Retention(RetentionPolicy.RUNTIME) //해당 annotation이 어느 시점에 실행되는지
public @interface PasswordValidator {
    String message() default "Please choose a strong password"; //기본 에러 메시지
    Class<?>[] groups() default {}; //annotation 그룹화 가능(기본, 심화 등...)
    Class<? extends Payload>[] payload() default {}; //메타데이터 설정 가능

    /* 이외에도 regexp(), flags()로 입력 형태 제어 가능하다
    예시)
    String regexp() default "[a-z]+";
    Pattern.Flag[] flags() default { Pattern.Flag.CASE_INSENSITIVE }; */
}