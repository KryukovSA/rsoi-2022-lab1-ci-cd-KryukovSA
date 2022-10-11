package com.example.labaRsoii.service;

import com.example.labaRsoii.model.entity.Person;
import com.example.labaRsoii.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.labaRsoii.utils.TestUtils.initializePerson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PersonServiceTest {
    private PersonRepository repository;
    private PersonService service;
    private Person person;

    @BeforeEach
    void setUp(){
        person = initializePerson();
        repository = mock(PersonRepository.class);
        service = spy(new PersonService(repository));
    }

    @Test
    public void when_getPerson_thenSuccess(){
        when(repository.getPersonById(1)).thenReturn(person);
        assertEquals(person, service.getPerson(1));
        verify(repository, times(1)).getPersonById(1);
    }

    @Test
    public void when_getPerson_thenNotFound(){
        when(repository.getPersonById(1)).thenReturn(null);
        assertThrows(NullPointerException.class, () -> service.getPerson(1));
        verify(repository, times(1)).getPersonById(1);
    }

    @Test
    public void useNumberOfInteractions_Save() {
        service.savePerson(person);
        verify(repository, times(1)).save(person);
    }

    @Test
    public void useNumberOfInteractions_NotSave(){
        person.setAge(-10);
        try {
            service.savePerson(person);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex);
        }
        verify(repository, times(0)).save(person);
    }

}
