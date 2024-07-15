package com.devyurakim.devschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"", "/", "home"})
    public String displayHomePage(Model model){
        //Model: Interface acting as a container for data
        model.addAttribute("username", "Yura Kim");
        return "home.html";
    }

}
