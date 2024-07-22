package com.devyurakim.devschool.controller;

import com.devyurakim.devschool.model.Person;
import com.devyurakim.devschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(person.getEazyClass()!=null && person.getEazyClass().getName()!=null){
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
        session.setAttribute("loggedInPerson", person);
        //throw new RuntimeException("Pain is killing me");
        return "dashboard.html";
    }

}
