package com.devyurakim.devschool.controller;

import com.devyurakim.devschool.model.Person;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/displayCourses")
    public String displayCourses(HttpSession session, Model model){

        Person person = (Person) session.getAttribute("loggedInPerson");
        model.addAttribute("person", person);

        return "courses_enrolled.html";
    }

}
