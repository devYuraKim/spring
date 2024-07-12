package com.devyurakim.devschool.controller;

import com.devyurakim.devschool.model.Contact;
import com.devyurakim.devschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String displayContactPage(Model model){
        /**user가 /contact로 naviage하면 새로운 Contact instance를 생성해서 model에 더하면
         * (model이 automatic data binding을 가능하게 한다)
         * 해당 object와 input form fields 간의 data binding을 시행한다*/
         model.addAttribute("contact", new Contact());
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
        public String saveMessage(@Valid @ModelAttribute Contact contact, Errors errors){
            if(errors.hasErrors()){
                log.error("Contact form validation failed due to: "+errors.toString());
                return "contact.html";
                //redisplay contact.html view with error messages (@Valid가 가능하게 함)
            }
            contactService.saveMessageDetails(contact);
            //contactService.setCounter(contactService.getCounter()+1);
            //log.info("Number of times contact form is submitted: "+contactService.getCounter());
            //ContactService가 @RequestScope면 매 HTTP request마다 counter가 0으로 refresh됨.
            //@SessionScope면 하나씩 증가되지만, cookie 삭제하면 counter가 0으로 refresh됨.
            return "redirect:/contact";
        }

        @GetMapping("/displayMessages")
        public String displayMessages(Model model) {
            List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
            model.addAttribute("contactMsgs",contactMsgs);
            return "messages.html";
        }

        @GetMapping(value = "/closeMsg")
        public String closeMsg(@RequestParam int id, Authentication authentication) {
            contactService.updateMsgStatus(id,authentication.getName());
            return "redirect:/displayMessages";
        }

}
