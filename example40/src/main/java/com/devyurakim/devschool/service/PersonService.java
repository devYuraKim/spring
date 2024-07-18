package com.devyurakim.devschool.service;

import com.devyurakim.devschool.constants.DevSchoolConstants;
import com.devyurakim.devschool.model.Person;
import com.devyurakim.devschool.model.Roles;
import com.devyurakim.devschool.repository.PersonRepository;
import com.devyurakim.devschool.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        //set Role
        Roles role = rolesRepository.getByRoleName(DevSchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        //encode Password
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }

}
