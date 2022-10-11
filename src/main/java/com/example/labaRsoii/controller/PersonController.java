package com.example.labaRsoii.controller;

import com.example.labaRsoii.model.entity.Person;
import com.example.labaRsoii.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/persons")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable final int id) {
        log.info("GET person by id:" + id);
        try {
            personService.getPerson(id);
            return ResponseEntity.ok(personService.getPerson(id));
        } catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        log.info("GET all persons");
        try {
            return ResponseEntity.ok(personService.findAll());
        } catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        log.info("POST info " + person);
        try {
            Person createdPerson = (Person) personService.savePerson(person);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Location", "/api/v1/persons/" + createdPerson.getId())
                    .build();
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable final int id) {
        log.info("DELETE info by key " + id);
        try {
            personService.deletePersonById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NullPointerException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable final int id, @RequestBody Person person) {
        log.info("PATCH info " + person);
        try {
            Person updatingPerson = personService.updatePersonById(id, person);
            return ResponseEntity.ok(updatingPerson);
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
