package de.accesor.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.accesor.csv.DataSourceAccessHandler;
import de.accesor.person.Person;

@RestController

public class PersonController {
	// public DataSourceAccessHandler dataHandler = new CsvFileHandler();
	@Autowired
	private DataSourceAccessHandler dataHandler;

	@GetMapping(path = "/persons")
	public ResponseEntity<List<Person>> getPerson() throws IOException {
		try {
			return new ResponseEntity<List<Person>>(dataHandler.getAllPersons(), HttpStatus.OK);
		}

		catch (Exception e) {
			throw e;

		}
	}

	@GetMapping(path = "/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) throws IOException {
		try {
			return new ResponseEntity<Person>(dataHandler.readColour(id), HttpStatus.OK);
		}

		catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/persons")
	public ResponseEntity addPerson(@RequestBody Person person) {
		System.out.println(person.getName());
		dataHandler.addPerson(person);
		return new ResponseEntity(HttpStatus.OK);

	}

	@GetMapping(path = "/persons/colour")
	public ResponseEntity<List<Person>> getPersonByColour(@RequestParam(name = "colour") String colour)
			throws IOException {
		return new ResponseEntity<List<Person>>(dataHandler.readColour(colour), HttpStatus.OK);
	}

	
	}


