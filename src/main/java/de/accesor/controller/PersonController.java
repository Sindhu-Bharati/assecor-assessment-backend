package de.accesor.controller;

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

import de.accesor.models.Person;
import de.accesor.services.PersonService;

@RestController

public class PersonController {
    private final PersonService personService;

    @Autowired
    PersonController(final PersonService personService) {
        this.personService = personService;
    }

	@GetMapping(path = "/persons")
	public ResponseEntity<List<Person>> getPerson() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
	}

	@GetMapping(path = "/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable long id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/persons")
	public ResponseEntity addPerson(@RequestBody Person person) {
		System.out.println(person.getName());
		personService.addPerson(person);
		return new ResponseEntity(HttpStatus.OK);

	}

	@GetMapping(path = "/persons/colour")
	public ResponseEntity<List<Person>> getPersonByColour(@RequestParam(name = "colour") String colour){
		return new ResponseEntity<>(personService.getPersonByColour(colour), HttpStatus.OK);
			
			}
	
}


