package com.devyurakim.devschool.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int classId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;


    /*mappedBy is for Parent Entity class*/
    @OneToMany(mappedBy = "eazyClass", cascade = CascadeType.PERSIST,targetEntity = Person.class)
    @JsonManagedReference
    private Set<Person> persons;
}
