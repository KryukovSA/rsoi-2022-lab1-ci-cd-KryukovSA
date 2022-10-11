package com.example.labaRsoii.repository;

import com.example.labaRsoii.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person getPersonById(int id);
    List<Person> findAll();
    void deletePersonById(int id);
}

