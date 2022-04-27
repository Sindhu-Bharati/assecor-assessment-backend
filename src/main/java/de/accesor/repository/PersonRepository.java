package de.accesor.repository;

import java.io.IOException;
import java.util.List;

import de.accesor.models.Person;

public interface PersonRepository {

    List<Person> getAllPersons();

    Person getPersonById(long id);

   List<Person> getPersonByColour(String colour) ;

    void addPerson(Person person);
}
