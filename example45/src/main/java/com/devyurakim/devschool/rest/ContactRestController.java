package com.devyurakim.devschool.rest;

import com.devyurakim.devschool.constants.DevSchoolConstants;
import com.devyurakim.devschool.model.Contact;
import com.devyurakim.devschool.model.Response;
import com.devyurakim.devschool.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/contact")
public class ContactRestController {

    /*RequestEntity: client에 의해 보내진 HTTP request 전체를 capture
    * ResponseEntity: server에서 보내는 HTTP response 전체
    * @RequestParam: web request에서 특정 param을 extract해서 method param과 binding
    * @RequestBody: HTTP request의 JSON, XML payload를 Java object로 역직렬화
    * @RequestHeader: HTTP request로부터 특정 header를 추출
    * @ResponseBody: server에서 view template 없이 바로 data를 보내려고 할 때 사용*/

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    //@ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam String status){
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
    //@ResponseBody
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact){
        if(contact!=null && contact.getStatus()!=null){
            return contactRepository.findByStatus(contact.getStatus());
        }else{
            return List.of();
        }
    }

    /*RequestHeader: trying to catch a particular HEADER
    * RequestBody: trying to catch a particular BODY*/
    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(@RequestHeader String invocationFrom, @Valid @RequestBody Contact contact){
        log.info(String.format("Header invocationFrom = %s", invocationFrom));
        contactRepository.save(contact);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).header("isMsgSaved", "true").body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity){
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value) -> {log.info(String.format("Header '%s' = '%s'", key, value.stream().collect(Collectors.joining("|"))));});
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());

        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully deleted");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        if(contact.isPresent()){
            contact.get().setStatus(DevSchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        }else{
            response.setStatusCode("400");
            response.setStatusMsg("Invalid Contact Id received");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
