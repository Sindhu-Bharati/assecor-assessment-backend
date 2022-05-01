package de.accesor.repository;

import java.util.List;

import de.accesor.entities.Person;

public interface PersonRepository {

    List<Person> getAllPersons();

    Person getPersonById(long id);

    List<Person> getPersonByColour(String colour) ;

    void addPerson(Person person);
}
