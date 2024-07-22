package com.devyurakim.devschool.controller;

import com.devyurakim.devschool.model.Person;
import com.devyurakim.devschool.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    PersonService personService;

    @GetMapping("/register")
    public String displayRegisterPage(Model model){
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute Person person, Errors errors, Model model) {
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getGlobalErrors()) {
                if ("FieldsValueMatch".equals(error.getCode())) {
                    String field = error.getArguments()[1].toString();
                    String message = error.getDefaultMessage();
                    if ("pwd".equals(field)) {
                        model.addAttribute("passwordMismatchError", message);
                    }
                    if ("email".equals(field)) {
                        model.addAttribute("emailMismatchError", message);
                    }
                }
            }
            log.error("Register form validation failed due to: " + errors.getAllErrors());
            return "register.html";
        }
        boolean isSaved = personService.createNewPerson(person);
        if(isSaved){
            return "redirect:/login?register=true";
        }else {
            return "register.html";
        }
    }

}