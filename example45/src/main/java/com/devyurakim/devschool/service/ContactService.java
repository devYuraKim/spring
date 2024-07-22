package com.devyurakim.devschool.service;

import com.devyurakim.devschool.constants.DevSchoolConstants;
import com.devyurakim.devschool.model.Contact;
import com.devyurakim.devschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        //audit //contact.setCreatedBy(DevSchoolConstants.ANONYMOUS);
        //audit //contact.setCreatedAt(LocalDateTime.now());
//        int result = contactRepository.saveContactMsg(contact);
//        if(result>0) {
//            isSaved = true;
//        }
        Contact savedContact = contactRepository.save(contact);
        if(savedContact != null && savedContact.getContactId()>0){
            isSaved = true;
        }
        return isSaved;
    }

//    public List<Contact> findMsgsWithOpenStatus(){
////        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(DevSchoolConstants.OPEN);
//        List<Contact> contactMsgs = contactRepository.findByStatus(DevSchoolConstants.OPEN);
//        return contactMsgs;
//    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        /* public static PageRequest of(int pageNumber, int pageSize, Sort sort) {
        return new PageRequest(pageNumber, pageSize, sort); }
        >> pageNumber: index of the page to be retrived
           PageRequest class의 static method of의 page는 0부터 시작! 그래서 첫 페이지pageNum(==1)를 로딩하고 싶으면 -1 해야 처음부터 나온다
        >> pageSize: 한 페이지에 포함되는 item의 개수
        */

        Pageable pageable = PageRequest.of(pageNum-1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(DevSchoolConstants.OPEN, pageable);
        //NamedQueries //Page<Contact> msgPage = contactRepository.findOpenMsgs(DevSchoolConstants.OPEN, pageable);
        //NamedQueriesNative //Page<Contact> msgPage = contactRepository.findOpenMsgsNative(DevSchoolConstants.OPEN, pageable);
        return msgPage;
    }

    /*JPA에서 update할 때는 기존의 객체를 찾아와서 수정이 필요한 부분 처리하고 그 객체를 다시 저장*/
    public boolean updateMsgStatus(int contactId){
    //audit //public boolean updateMsgStatus(int contactId, String updatedBy){
        boolean isUpdated = false;
//        int result = contactRepository.updateMsgStatus(contactId,DevSchoolConstants.CLOSE, updatedBy);
//        if(result>0) {
//            isUpdated = true;
//        }

//  updateQuery 사용하면 아래 코드 다 필요 없음
//        Optional<Contact> contact = contactRepository.findById(contactId);
//        /*Optional.ofNullable() vs Optional<T>
//        * no need to wrap contactRepository.findById(contactId)
//        * 왜냐면 위 함수의 return type이 이미 Optional<T>이기 때문임!*/
//        contact.ifPresent(contact1 -> {
//            contact1.setStatus(DevSchoolConstants.CLOSE);
//        /* audit
//        contact1.setUpdatedBy(updatedBy);
//        contact1.setUpdatedAt(LocalDateTime.now());
//        */
//        });
//        Contact updatedContact = contactRepository.save(contact.get());
//        if(updatedContact!=null && updatedContact.getUpdatedBy()!=null){
//            isUpdated=true;
//        }

        //int rows = contactRepository.updateStatusById(DevSchoolConstants.CLOSE, contactId);
        //NamedQueries // int rows = contactRepository.updateMsgStatus(DevSchoolConstants.CLOSE, contactId);
        int rows = contactRepository.updateMsgStatusNative(DevSchoolConstants.CLOSE, contactId);
        if(rows>0) isUpdated=true;

        return isUpdated;
    }
}
