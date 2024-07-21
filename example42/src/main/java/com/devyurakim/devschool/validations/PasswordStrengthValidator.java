package com.devyurakim.devschool.validations;

import com.devyurakim.devschool.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

/*validation logic을 정의하는 class로, 다음 interface를 구현한다
* ContraintValidator<customAnnotation이름, targetType> */
public class PasswordStrengthValidator implements
        ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;

    /*initialize method는 custom validaiton을 위한 data를 초기 세팅하는 역할을 수행
    * default void initialize(A constraintAnnotation) */
    @Override
    public void initialize(PasswordValidator passwordValidator) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    /*이게 validation logic임
    * boolean isValid(T value, ConstraintValidatorContext context); */
    @Override
    public boolean isValid(String passwordField,
                           ConstraintValidatorContext cxt) {
        /*결국 확인하려는 내용은
        (1)입력된 password가 null인가,
        (2)입력된 password 중 weakPassword와 일치하는 것이 있는가*/
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }
}
