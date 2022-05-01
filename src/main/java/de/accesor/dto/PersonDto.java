package de.accesor.dto;

import java.util.Objects;

public class PersonDto {

    private Long id;
    private String name;
    private String lastName;
    private String zipCode;
    private String city;
    private String colour;

    public PersonDto() {

    }
    public PersonDto(Long id, String name, String lastName, String zipCode, String city, String colour) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.zipCode = zipCode;
        this.city = city;
        this.colour = colour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(id, personDto.id) && Objects.equals(name, personDto.name) && Objects.equals(lastName, personDto.lastName)
               && Objects.equals(zipCode, personDto.zipCode) && Objects.equals(city, personDto.city) && Objects.equals(colour, personDto.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, zipCode, city, colour);
    }

    @Override
    public String toString() {
        return "PersonDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", lastName='" + lastName + '\'' +
               ", zipCode='" + zipCode + '\'' +
               ", city='" + city + '\'' +
               ", colour='" + colour + '\'' +
               '}';
    }
}
