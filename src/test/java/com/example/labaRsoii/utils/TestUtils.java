package com.example.labaRsoii.utils;

import com.example.labaRsoii.model.entity.Person;

public class TestUtils {
    public static Person initializePerson() {
        Person person = new Person();
        person.setId(1);
        person.setWork("intel");
        person.setName("box");
        person.setAddress("moscow");
        person.setAge(43);
        return person;
    }
}
