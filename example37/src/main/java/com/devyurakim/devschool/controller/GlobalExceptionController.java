package com.devyurakim.devschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* @ControllerAdvice is a specialization of the @Component annotation
which allows to handle exceptions across the whole application in one global handling component.
It can be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar. */
@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    /*
    @ExceptionHandler will register the given method for a given exception type,
    so that ControllerAdvice can invoke this method logic
    if a given exception type is thrown inside the web application.
    */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception, Model model){
        model.addAttribute("errormsg", exception.getMessage());
        return "error.html";
    }

}
