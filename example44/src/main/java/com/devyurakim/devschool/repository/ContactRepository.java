package com.devyurakim.devschool.repository;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
//@Repository
//public class ContactRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public ContactRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int saveContactMsg(Contact contact){
//        String sql = "INSERT INTO CONTACT_MSG (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS," +
//                "CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),
//                contact.getEmail(),contact.getSubject(),contact.getMessage(),
//                contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
//    }
//
//    public List<Contact> findMsgsWithStatus(String status) {
//        String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
//        return jdbcTemplate.query(sql,new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, status);
//            }
//        },new ContactRowMapper());
//    }
//
//    public int updateMsgStatus(int contactId, String status,String updatedBy) {
//        String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_BY = ?,UPDATED_AT =? WHERE CONTACT_ID = ?";
//        return jdbcTemplate.update(sql,new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, status);
//                preparedStatement.setString(2, updatedBy);
//                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//                preparedStatement.setInt(4, contactId);
//            }
//        });
//    }
//
//}

import com.devyurakim.devschool.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    /* Pageable을 사용하면 Page로 Entity를 감쌀 것
    * In Spring Data JPA, when you include a Pageable parameter in a repository method, the return type should be a Page object.
    * This pattern is consistent regardless of the specific query method defined. */
    //@Query("SELECT c FROM Contact c WHERE c.status = :status")
    //:paramName -- named parameter
    @Query(nativeQuery = true, value="SELECT * FROM contact_msg c WHERE c.status = :status")
    Page<Contact> findByStatus(@Param("status") String state, Pageable pageable);


    Page<Contact> findOpenMsgs(String status, Pageable pageable);


//namedQueriesNative //@Query(nativeQuery = true)
//namedQueriesNative //Page<Contact> findOpenMsgsNative(String status, Pageable pageable);

    /* UDATE, DELETE, INSERT 할 때, 즉 DB의 상태를 변화시킬 때는 @Transactional, @Modifying 명시*/
    //?# -- positional parameter
//    @Transactional
//    @Modifying
//    @Query("UPDATE Contact c SET c.status =?1 WHERE c.contactId = ?2")
//    int updateStatusById(String status, int id);

//    @Transactional
//    @Modifying
//    int updateMsgStatus(String status, int id);

    @Transactional
    @Modifying
    @Query(nativeQuery=true)
    int updateMsgStatusNative(String status, int id);
}