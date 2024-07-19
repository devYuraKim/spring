package com.devyurakim.devschool.model;

import com.devyurakim.devschool.annotation.FieldsValueMatch;
import com.devyurakim.devschool.annotation.PasswordValidator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
/*you provide an array of @FieldsValueMatch annotations inside {}.*/
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "비밀번호 불일치"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "이메일 주소 불일치"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int personId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 11 digits")
    private String mobileNumber;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @NotBlank(message="Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address" )
    @Transient //DB에 반영하지 않는 경우 사용
    private String confirmEmail;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient //DB에 반영하지 않는 경우 사용
    private String confirmPwd;

    //Parent Entity에만 정의했으므로 uni-directional

    /*
    >> JoinColumn: specifies foreign key column
    >> name: name of the foreign key column
    >> referencedColumnName: field name of the target entity class

    >> cascade: propogate entity state changes from Parent to Child
    >> orphanRemoval: Parent collection으로부터 Child entity가 지워졌을 때, 즉 둘 사이의 연관관계가 지워졌을 때,
    child entity를 DB에서 삭제할 것인지 아닌지를 결정
    */

    /*OneToOne으로 정의하면 해당 foreign key column에 자동으로 unique constraint가 생겨 진짜로 1:1 관계를 맺게 해준다.
    * 그래서 OneToOne으로는 각 role 당 하나씩의 person만 생성 가능함! */
    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    /*
     >> class가 없을 수도 있으니(ex 가입 시 혹은 수강신청 안 하는 경우)  optional=null
     >> default fetch type은 EAGER이지만 여기서는 '그냥' LAZY로 설정
     >> class 저장 시에 person entity와 관계가 전혀 없으므로 cascade는 별도 설정 없음
    */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    @JsonBackReference
    private EazyClass eazyClass;

}
