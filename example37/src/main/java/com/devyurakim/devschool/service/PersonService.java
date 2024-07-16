package com.devyurakim.devschool.service;

import com.devyurakim.devschool.model.Person;
import com.devyurakim.devschool.repository.PersonRepository;
import com.devyurakim.devschool.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        //Roles role = rolesRepository.getByRoleName(DevSchoolConstants.STUDENT_ROLE);
        //person.setRoles(role);
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }

}
