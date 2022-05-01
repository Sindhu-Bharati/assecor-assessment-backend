package de.accesor.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.accesor.dto.PersonDto;
import de.accesor.mappers.PersonMapper;
import de.accesor.entities.Person;
import de.accesor.repository.csv.CsvFileRepository;
import de.accesor.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(final CsvFileRepository dataSourceReader) {
        this.personRepository = dataSourceReader;
    }

    public List<PersonDto> getAllPersons() {
        List<Person> personList = this.personRepository.getAllPersons();
        return personList.stream().map(PersonMapper::mapPersonEntityToPersonDto).collect(Collectors.toList());
    }

    public PersonDto getPersonById(long id) {
        Person person =  this.personRepository.getPersonById(id);
        return PersonMapper.mapPersonEntityToPersonDto(person);
    }

	public List<PersonDto> getPersonByColour(String colour) {
        List<Person> personList = this.personRepository.getPersonByColour(colour);
        return personList.stream().map(PersonMapper::mapPersonEntityToPersonDto).collect(Collectors.toList());

    }

	public int addPerson(PersonDto person) {
		this.personRepository.addPerson(PersonMapper.mapPersonDtoToPersonEntity(person));
        return getAllPersons().size();
	}
}
