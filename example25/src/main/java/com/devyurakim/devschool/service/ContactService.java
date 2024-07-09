package com.devyurakim.devschool.service;

import com.devyurakim.devschool.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {

    /**(1)Logger 생성 코드도 boiler plate code임
     * (2)class 이름 안 바꿔도 exception 발생하지 않아서 logging 놓치는 것도 모름
     * 결론 > @Sl4j 사용하자 */
    //Logger log = LoggerFactory.getLogger(ContactService.class);

    private int counter = 0;

    public ContactService(){
        System.out.println("contact service bean initialized");
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        //TODO - Need to persist the data into the DB table
        log.info(contact.toString());
        return isSaved;
    }
}
