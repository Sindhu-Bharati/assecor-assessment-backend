package de.accesor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.accesor.models.Person;
import de.accesor.repository.csv.CsvFileRepository;
import de.accesor.repository.PersonRepository;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(CsvFileRepository dataSourceReader) {
        this.personRepository = dataSourceReader;
    }

    public List<Person> getAllPersons() {
        return this.personRepository.getAllPersons();
    }

    public Person getPersonById(long id) {
        return this.personRepository.getPersonById(id);
    }

	public List<Person> getPersonByColour(String colour) {
		return this.personRepository.getPersonByColour(colour);
	}

	public void addPerson(Person person) {
		this.personRepository.addPerson(person);		
	}
}
