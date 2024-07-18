package com.devyurakim.devschool.validations;

import com.devyurakim.devschool.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

//두 개의 필드를 비교할 것이기 때문에 어떤 type일지 확정할 수 없어 Object로 설정
public class FieldsValueMatchValidator
        implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        /* BeanWrapperImpl is an helper class present inside Spring framework which help us to get and set the values inside java objects by writing a generic code.
        For example, if you look at the below code, we are passing an object with the variable name value and inside this object we are trying to fetch the field value with a fieldname variable as field.
        So without worrying to write the actual object and get a value from it, we can write a generic code using BeanWrapperImpl. */

        /* BeanWrapperImpl can be used with any Java object that follows JavaBean conventions.
        JavaBean conventions imply that the object has:
        (1) Private fields that are accessed through getter and setter methods.
        (2) Public no-argument constructor. */

        /*BeanWrapperImpl allows you to work with object properties using their names as strings.
        This is particularly useful in scenarios where you need to interact with properties dynamically,
        especially when you don't know the property names at compile-time or
        when you need to perform operations generically across multiple objects.*/

        Object fieldValue = new BeanWrapperImpl(value) //value object에 대해 BeanWrapperImple instance를 생성 > value object에 대한 properties와 dynamic하게 상호작용 가능함
                .getPropertyValue(field); //value object의 field properties의 value를 회수함
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
//        application.properties에서 spring.jpa.properties.jakarta.persistence.validation.mode=none으로 설정하면 아래 코드 없어도 됨
//        if (fieldValue != null) {
//            if(fieldValue.toString().startsWith("$2a")){
//                /*$2a BCryptHash면 이걸로 시작을 한대...
//                * BCryptPasswordEncoder class에 가서 public static enum BCryptVersion 보면
//                * $2A("$2a"), $2Y("$2y"), $2B("$2b") 이렇게 있음! */
//                return true;
//                /*이렇게 해도 괜찮은 게 plain text input value는 이미 검증 끝난 상태임!*/
//            }else {
//                return fieldValue.equals(fieldMatchValue);
//            }
//        } else {
//            return fieldMatchValue == null;
//        }
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
