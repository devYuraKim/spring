package com.devyurakim.devschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**Spring Boot로 form input data와 POJO data binding을 자동으로 하려면
 * (0) spring-boot-starter-web dependency가 있어야 하고
 * (1) form input의 filed name과 POJO class의 field name이 일치해야 하고
 * (2) POJO class에 setter/getter가 있어야 한다.
 * */

@Data
@Entity
@Table(name="contact_msg")
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int contactId;
    /*
        * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
          @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
          @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
        * */
    @NotBlank(message="이름은 공란으로 둘 수 없습니다.")
    @Size(min=3, message="이름은 최소 3글자 이상이어야 합니다.")
    private String name;

    @NotBlank(message="휴대전화 번호는 공란으로 둘 수 없습니다.")
    @Pattern(regexp="(^$|[0-9]{11})",message = "휴대전화 번호는 11자리여야 합니다.")
    private String mobileNum;

    @NotBlank(message="이메일은 공란으로 둘 수 없습니다.")
    @Email(message = "이메일 주소 형식을 확인해주세요." )
    private String email;

    @NotBlank(message="제목은 공란으로 둘 수 없습니다.")
    @Size(min=5, message="제목은 최소 5글자 이상이어야 합니다.")
    private String subject;

    @NotBlank(message="내용은 공란으로 둘 수 없습니다.")
    @Size(min=10, message="내용은 최소 10글자 이상이어야 합니다.")
    private String message;

    private String status;

}
