package de.accesor.personapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import de.accesor.dto.PersonDto;

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

		final PersonDto expectedPerson1= new PersonDto(1L, " Peter", "Petersen", "18439", "Stralsund test", "grün");
		final PersonDto expectedPerson2= new PersonDto(2L," Johnny", "Johnson", "88888", "made up", "grün");

        final HttpEntity<PersonDto> personEntity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<List<PersonDto>> response=REST_TEMPLATE.exchange(getBaseUrl()+"/persons", HttpMethod.GET, personEntity,new ParameterizedTypeReference<>(){});
		assertEquals(4,response.getBody().size());
		assertEquals(expectedPerson1,response.getBody().get(0));
		assertEquals(expectedPerson2,response.getBody().get(1));
	}



	@Test
	void getPersonsById_returnsExpectedPerson_ok() {

		final PersonDto expectedPerson= new PersonDto(1L," Peter", "Petersen", "18439", "Stralsund test", "grün");
		final HttpEntity<PersonDto> personEntity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<PersonDto> response=REST_TEMPLATE.exchange(getBaseUrl()+"/persons/1", HttpMethod.GET, personEntity, PersonDto.class);
		assertEquals(expectedPerson, response.getBody());
	}

	@Test
	void  getPersonsByColour_returnsExpectedPersons_multiplePersonsWithSameColour() {
		final PersonDto expectedPerson1= new PersonDto(1L," Peter", "Petersen", "18439", "Stralsund test", "grün");
		final PersonDto expectedPerson2= new PersonDto(2L," Johnny", "Johnson", "88888", "made up", "grün");
		final HttpEntity<PersonDto> personEntity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<List<PersonDto>> response=REST_TEMPLATE.exchange(getBaseUrl()+"/persons/colour?colour=grün", HttpMethod.GET, personEntity,new ParameterizedTypeReference<>(){});

        assertEquals(expectedPerson1, response.getBody().get(0));
		assertEquals(expectedPerson2,response.getBody().get(1));
		assertEquals(2,response.getBody().size());
	}
}
