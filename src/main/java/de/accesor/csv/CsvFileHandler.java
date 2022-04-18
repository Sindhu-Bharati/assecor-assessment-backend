package de.accesor.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.accesor.person.*;

@Service
public class CsvFileHandler implements DataSourceAccessHandler {
	
	private String inputFilePath;
	
	public CsvFileHandler(@Value("${input.csv.file}") String inputfile) {
		this.inputFilePath= inputfile;
		
	}

	public List<Person> getAllPersons() throws IOException {

		List<Person> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
			String line;
			Long personid = 1L;
			while ((line = br.readLine()) != null) {
				String[] person = line.split(",");
				//String[] address = line.split(" ");
				String[] addressnew = person[2].trim().split(" ");
				String zipCode= addressnew[0];
				String city= String.join(",",Arrays.copyOfRange(addressnew, 1, addressnew.length)).replace(",", " ");
				records.add(new Person(personid++, person[1], person[0], zipCode, city,
						new IdToColourMapper().getColour(person[3])));
			}
		}

		System.out.print(records);

		return records;
	}

	public Person readColour(Long id) throws IOException {
		List<Person> persons = getAllPersons();
		for (Person p : persons) {
			if (id == p.getId())
				return p;
		}
		throw new IllegalArgumentException("User not found");
	}

	public List<Person> readColour(String colour) throws IOException {
		System.out.println("*");
		getAllPersons().stream().filter(value -> value.getColour().equals(colour)).forEach(val ->System.out.println(val));
		System.out.println("-");
		return getAllPersons().stream().filter(value -> value.getColour().equals(colour)).collect(Collectors.toList());

	}

	public void addPerson(Person person) {
		FileWriter fw;
		try {
			fw = new FileWriter(inputFilePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(person.getName());
			bw.write(", ");
			bw.write(person.getLastName());
			bw.write(", ");
			bw.write(person.getZipCode());
			bw.write(person.getCity());
			bw.write(", ");
			bw.write(new IdToColourMapper().getId(person.getColour()));
			bw.newLine();
			bw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
		

}
