package com.devyurakim.devschool.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Roles extends BaseEntity {

    /*Parent Entity에만 정의했으므로 uni-directional*/

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int roleId;

    private String roleName;

    /*Q.Why do we create roles table and not enum value roles in Person table
    * It is always advisable to maintain the ROLES information in a separate rather than maintaining them in a Enum class.
    * Since a single person may have multiple roles, it is common to create them in a separate table instead of inside the Person table.*/

}