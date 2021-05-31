package com.vidula.service;

import com.vidula.model.Person;
import com.vidula.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    @Autowired
    PersonRepository personRepository;
    
    public Person execute(Person person){
        
        Person newPerson = personRepository.save(person);
        
        return newPerson;
    }
}
