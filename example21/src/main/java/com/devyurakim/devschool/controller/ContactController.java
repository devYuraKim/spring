package com.devyurakim.devschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    //Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private static Logger log = LoggerFactory.getLogger(ContactController.class);

        @RequestMapping("/contact")
        public String displayContactPage(){
            return "contact.html";
        }

        @PostMapping("/saveMsg")
        public String saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                  @RequestParam String email, @RequestParam String subject,
                                  @RequestParam String message){
            log.info("Name : " + name);
            log.info("Mobile Number : " + mobileNum);
            log.info("Email Address : " + email);
            log.info("Subject : " + subject);
            log.info("Message : " + message);
            return "redirect:/contact";
        }

}
