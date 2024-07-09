package com.devyurakim.devschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    //SecurityFilterChain에 설정한 /login?error=true인 경우, error에 true 대입
    //SecurityFilterChain에 설정한 /login?logout=true인 경우, logout에 true 대입
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout, Model model) {
        String errorMessage = null;
        String logoutMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            logoutMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("logoutMessage", logoutMessage);
        return "login.html";
    }

}