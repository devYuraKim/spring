package com.devyurakim.devschool.service;

import com.devyurakim.devschool.constants.DevSchoolConstants;
import com.devyurakim.devschool.model.Contact;
import com.devyurakim.devschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {

    /**(1)Logger 생성 코드도 boiler plate code임
     * (2)class 이름 안 바꿔도 exception 발생하지 않아서 logging 놓치는 것도 모름
     * 결론 > @Sl4j 사용하자 */
    //Logger log = LoggerFactory.getLogger(ContactService.class);

    //scope 파악용 코드
    //private int counter = 0;
    //public int getCounter() {
    //    return counter;
    //}
    //public void setCounter(int counter) {
    //    this.counter = counter;
    //}

    //public ContactService(){
    //    System.out.println("contact service bean initialized");
    //}

    //public boolean saveMessageDetails(Contact contact){
    //    boolean isSaved = true;
    //    //TODO - Need to persist the data into the DB table
    //    log.info(contact.toString());
    //    return isSaved;
    //}

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(DevSchoolConstants.OPEN);
        contact.setCreatedBy(DevSchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if(result>0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(DevSchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId,DevSchoolConstants.CLOSE, updatedBy);
        if(result>0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
