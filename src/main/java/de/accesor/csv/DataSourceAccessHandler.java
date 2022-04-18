package de.accesor.csv;

import java.io.IOException;
import java.util.List;

import de.accesor.person.Person;

public interface DataSourceAccessHandler {
	public List<Person> getAllPersons() throws IOException;

	public Person readColour(Long id) throws IOException;

	public List<Person> readColour(String colour) throws IOException;

	public void addPerson(Person person);

	

}
