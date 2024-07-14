package com.devyurakim.devschool.repository;

/*
@Repository stereotype annotation is used to add a bean of this class type
to the Spring context and indicate that given Bean is used to perform DB related operations */

//@Repository
//public class HolidaysRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public HolidaysRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Holiday> findAllHolidays() {
//        String sql = "SELECT * FROM HOLIDAYS";
//        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
//        return jdbcTemplate.query(sql, rowMapper);
//    }
//
//}

import com.devyurakim.devschool.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, String> {
}