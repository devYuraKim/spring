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

    /*두 개의 필드 입력값에 대해 비교를 실시할 것이므로 아래 코드가 필요함
    * 아래 List는 validation 적용하는 entity에서 아래처럼 정의할 예정
    * @FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "비밀번호 불일치!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "이메일 주소 불일치!"
        )
    })*/
    String field();
    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
    /*a meta-annotation used to group multiple instances of @FieldsValueMatch annotations together.
    This grouping allows you to apply multiple instances of @FieldsValueMatch to a target entity or field.*/

    /*In Java annotations, when an annotation type contains a single element, conventionally named value(),
    it allows you to specify one or more values for that element when using the annotation.
    This is a standard feature of annotations in Java.*/

    /*The value() method in an annotation serves as a placeholder to indicate where you can specify values when using the annotation.
    * @FieldsValueMatch.List({@FieldsValueMatch(...), @FieldsValueMatch(...)})
    * 위의 예시에서는 FieldsValueMatch.List({...})의 {...}가 value()가 가리키는 부분이라고 생각하면 됨*/

    /*Exactly, you've got the idea.
    In Java annotations, value() is indeed not a method that you call or invoke like a regular method using dot notation (.).
    Instead, it serves as a placeholder or a conventionally named method that indicates where you provide actual values when you use the annotation.*/

}
