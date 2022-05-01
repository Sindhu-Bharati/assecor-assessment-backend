package de.accesor.personapi.repository.csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.accesor.entities.Address;
import de.accesor.entities.Person;
import de.accesor.exceptions.CsvFileException;
import de.accesor.exceptions.PersonColourMappingException;
import de.accesor.repository.csv.CsvFileRepository;
import de.accesor.repository.csv.FileHandler;

@ExtendWith(MockitoExtension.class)
class CsvFileRepositoryTest {

    @Mock
    private FileHandler fileHandler;

    @InjectMocks
    private CsvFileRepository csvFileRepository;

    @Test
    void getAllPersons_returnsEmptyPersonList_whenInputFileIsEmpty() {
        when(fileHandler.getFileContent()).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), csvFileRepository.getAllPersons());
    }

    @Test
    void getAllPersons_throwsCsvFileException_whenInputFileIsHasInvalidDataFormat() {
        // when address is missing
        String invalidFileContent = "hans,muller,1";
        when(fileHandler.getFileContent()).thenReturn(List.of(invalidFileContent));

        assertThrows(CsvFileException.class, () -> csvFileRepository.getAllPersons());

    }

    @Test
    void getAllPersons_returnsListOfPersons_whenInputFileIsValid() {
        String fileContentLine1 = "muller,hans,12345 berlin, 1";
        String fileContentLine2 = "weiß,daniel,54321 munich, 2";
        String fileContentLine3 = "ford,henry,55555 erlangen, 3";

        Person expectedPerson1 = new Person(1L, "hans", "muller", "blau", new Address("12345", "berlin"));
        Person expectedPerson2 = new Person(2L, "daniel", "weiß", "grün", new Address("54321", "munich"));
        Person expectedPerson3 = new Person(3L, "henry", "ford", "violett", new Address("55555", "erlangen"));

        List<Person> expectedPersons = Arrays.asList(expectedPerson1, expectedPerson2, expectedPerson3);

        when(fileHandler.getFileContent()).thenReturn(Arrays.asList(fileContentLine1, fileContentLine2, fileContentLine3));

        assertEquals(expectedPersons, csvFileRepository.getAllPersons());

    }

    @Test
    void getAllPersons_throwsCsvFileException_whenIInvalidColorIdentifierIsFoundInCsvFile() {
        // 10 is an invalid index for color
        String fileContentLine1 = "muller,hans,12345 berlin, 10";
        when(fileHandler.getFileContent()).thenReturn(List.of(fileContentLine1));

        assertThrows(PersonColourMappingException.class, () -> csvFileRepository.getAllPersons());
    }
}
