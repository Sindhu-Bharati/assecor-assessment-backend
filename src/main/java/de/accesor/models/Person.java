package de.accesor.models;

import java.util.Objects;

public class Person {
	private Long id;
	private String name;
	private String lastName;
	private String zipCode;
	private String city;
	@Override
	public int hashCode() {
		return Objects.hash(city, colour, id, lastName, name, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(city, other.city) && Objects.equals(colour, other.colour) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(name, other.name)
				&& Objects.equals(zipCode, other.zipCode);
	}

	private String colour;

	public Person() {

	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastName=" + lastName + ", zipCode=" + zipCode + ", city="
				+ city + ", colour=" + colour + "]";
	}

	public Person(Long id, String name, String lastName, String zipCode, String city, String colour) {
		super();
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

}
