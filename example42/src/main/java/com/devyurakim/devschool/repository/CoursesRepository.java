package com.devyurakim.devschool.repository;

import com.devyurakim.devschool.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {

}
