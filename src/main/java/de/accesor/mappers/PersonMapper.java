package de.accesor.mappers;


import de.accesor.dto.PersonDto;
import de.accesor.entities.Address;
import de.accesor.entities.Person;

public class PersonMapper {

    private PersonMapper() {

    }
    public static PersonDto mapPersonEntityToPersonDto(final Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getLastName(), person.getAddress().getZipCode(), person.getAddress().getCity(), person.getColour());
    }

    public static Person mapPersonDtoToPersonEntity(final PersonDto person) {
        return new Person(person.getId(), person.getName(), person.getLastName(), person.getColour(), new Address(person.getZipCode(), person.getCity()));
    }
}
