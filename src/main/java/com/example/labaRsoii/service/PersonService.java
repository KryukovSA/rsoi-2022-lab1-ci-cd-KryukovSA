package com.example.labaRsoii.service;

import com.example.labaRsoii.model.entity.Person;
import com.example.labaRsoii.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public Person getPerson (int id) throws NullPointerException {
        Person person = personRepository.getPersonById(id);
        if(person != null){
            return person;
        }
        throw new NullPointerException();
    }

    public List<Person> findAll() throws NullPointerException{
        if(!personRepository.findAll().isEmpty()){
            return personRepository.findAll();
        }
        throw new NullPointerException();
    }

    @Transactional
    public Object savePerson (Person person) throws IllegalArgumentException {
        if(person == null || person.getAge() < 0 )
            throw new IllegalArgumentException("incorrect input");
        else {
            return personRepository.save(person);
        }
    }

    @Transactional
    public void deletePersonById(int id) throws NullPointerException{
        if (personRepository.findAll().contains(personRepository.getPersonById(id))) {
            personRepository.deletePersonById(id);
        } else {
            throw new NullPointerException();
        }
    }

    public Person updatePersonById(Integer id, Person person) throws IllegalArgumentException {
        Person existingPerson = personRepository.getPersonById(id);
        if (id == null || person.getAge() < 0 || person == null || existingPerson == null) {
            throw new IllegalArgumentException("incorrect input");
        } else {
            if (person.getAge() > 0) existingPerson.setAge(person.getAge());
            if (person.getName() != null) existingPerson.setName(person.getName());
            if (person.getWork() != null) existingPerson.setWork(person.getWork());
            if (person.getAddress() != null) existingPerson.setAddress(person.getAddress());
            return (Person) personRepository.save(existingPerson);
        }
    }



}
