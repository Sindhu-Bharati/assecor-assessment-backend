package de.accesor.repository.csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import de.accesor.exceptions.UserNotFoundException;
import de.accesor.repository.PersonRepository;
import de.accesor.util.IdToColourMapper;
import de.accesor.models.Person;

@Component
public class CsvFileRepository implements PersonRepository {

    private final FileHandler csvFileHandler;

    @Autowired
    public CsvFileRepository(final FileHandler fileWriter) {
        this.csvFileHandler= fileWriter;
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();

        long personId = 1L;
        for (String line: csvFileHandler.getFileContent()) {
            final String[] person = line.split(",");

            final String firstName = person[1];
            final String lastName = person[0];
            final String[] address = person[2].trim().split(" ");
            final String zipCode = address[0];
            final String city = String.join(",", Arrays.copyOfRange(address, 1, address.length)).replace(",", " ");
            final String color = IdToColourMapper.getColour(person[3]);

            persons.add(new Person(personId++, firstName, lastName, zipCode, city, color));
        }
        return persons;
    }

    public Person getPersonById(long id) {
    	return getAllPersons().stream().filter(value -> value.getId().equals(id)).findFirst().orElseThrow(() -> new UserNotFoundException("No user found with Id " + id));
    }

    public List<Person> getPersonByColour(String colour) {
    	return getAllPersons().stream().filter(value -> value.getColour().equals(colour)).collect(Collectors.toList());

    }

    public void addPerson(Person person) {
    	csvFileHandler.addPerson(person);


    }
}
