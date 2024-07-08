package com.devyurakim.devschool.model;

import lombok.Data;

/**Spring Boot로 form input data와 POJO data binding을 자동으로 하려면
 * (0) spring-boot-starter-web dependency가 있어야 하고
 * (1) form input의 filed name과 POJO class의 field name이 일치해야 하고
 * (2) POJO class에 setter/getter가 있어야 한다.
 * */

@Data
public class Contact {

    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;

}
