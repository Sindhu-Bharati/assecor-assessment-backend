package de.accesor.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.accesor.dto.PersonDto;
import de.accesor.services.PersonService;

@RestController

public class PersonController {
    private final PersonService personService;

    @Autowired
    PersonController(final PersonService personService) {
        this.personService = personService;
    }

	@GetMapping(path = "/persons")
	public ResponseEntity<List<PersonDto>> getPerson() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
	}

	@GetMapping(path = "/persons/{id}")
	public ResponseEntity<PersonDto> getPersonById(@PathVariable long id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/persons")
	public ResponseEntity addPerson(@RequestBody PersonDto person) {
		int locationId = personService.addPerson(person);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(locationId)
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

	@GetMapping(path = "/persons/colour")
	public ResponseEntity<List<PersonDto>> getPersonByColour(@RequestParam(name = "colour") String colour){
		return new ResponseEntity<>(personService.getPersonByColour(colour), HttpStatus.OK);
    }
	
}


