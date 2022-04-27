package de.accesor.personapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;


import de.accesor.models.Person;
	
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application.properties")
class PersonapiApplicationTests {

	private static final TestRestTemplate REST_TEMPLATE = new TestRestTemplate();
	@LocalServerPort
	private int port;
	private String getBaseUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	void getPersons_returnsExpectedPersons_ok() {
		final Person expectedPerson1= new Person(1L," Peter", "Petersen", "18439", "Stralsund test", "grün");
		final Person expectedPerson2= new Person(2L," Johnny", "Johnson", "88888", "made up", "violett");
		final HttpEntity<Person> personEntity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<List<Person>> response=REST_TEMPLATE.exchange(getBaseUrl()+"/persons", HttpMethod.GET, personEntity,new ParameterizedTypeReference<>(){});
		assertEquals(2,response.getBody().size());
		assertEquals(expectedPerson1,response.getBody().get(0));
		assertEquals(expectedPerson2,response.getBody().get(1));
	}



	@Test
	void getPersonsById_returnsExpectedPerson_ok() {

		final Person expectedPerson= new Person(1L," Peter", "Petersen", "18439", "Stralsund test", "grün");
		final HttpEntity<Person> personEntity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<Person> response=REST_TEMPLATE.exchange(getBaseUrl()+"/persons/1", HttpMethod.GET, personEntity,Person.class);
		assertEquals(expectedPerson, response.getBody());
	}

	@Test
	void  getPersonsByColour_returnsExpectedPersons_multiplePersonsWithSameColour() {
		final Person expectedPerson1= new Person(1L," Peter", "Petersen", "18439", "Stralsund test", "grün");
		final Person expectedPerson2= new Person(2L," Johnny", "Johnson", "88888", "made up", "grün");
		final HttpEntity<Person> personEntity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<List<Person>> response=REST_TEMPLATE.exchange(getBaseUrl()+"/persons/colour?colour=grün", HttpMethod.GET, personEntity,new ParameterizedTypeReference<>(){});
		assertEquals(expectedPerson1, response.getBody().get(0));
		assertEquals(expectedPerson2,response.getBody().get(1));
		assertEquals(2,response.getBody().size());
	}
}
