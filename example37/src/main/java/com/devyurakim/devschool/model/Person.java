package com.devyurakim.devschool.model;

import com.devyurakim.devschool.annotation.FieldsValueMatch;
import com.devyurakim.devschool.annotation.PasswordValidator;
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

    @OneToOne(cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
    private Address address;

}
