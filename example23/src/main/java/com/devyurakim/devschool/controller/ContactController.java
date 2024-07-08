package com.devyurakim.devschool.controller;

import com.devyurakim.devschool.model.Contact;
import com.devyurakim.devschool.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ContactController {


    //Logger log = LoggerFactory.getLogger(this.getClass().getName());
    //private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
        public String displayContactPage(){
            return "contact.html";
        }

        /**개별 Parameter를 처리하려면 번거로우므로 POJO 사용하는 코드로 변경*/
//        @PostMapping("/saveMsg")
//        public String saveMessage(@RequestParam String name, @RequestParam String mobileNum,
//                                  @RequestParam String email, @RequestParam String subject,
//                                  @RequestParam String message){
//            log.info("Name : " + name);
//            log.info("Mobile Number : " + mobileNum);
//            log.info("Email Address : " + email);
//            log.info("Subject : " + subject);
//            log.info("Message : " + message);
//            return "redirect:/contact";
//        }

        @PostMapping("/saveMsg")
        public String saveMessage(Contact contact){
            contactService.saveMessageDetails(contact);
            return "redirect:/contact";
        }
}
