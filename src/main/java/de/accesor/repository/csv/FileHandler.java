package de.accesor.repository.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.accesor.exceptions.CsvFileException;
import de.accesor.entities.Person;
import de.accesor.mappers.IdToColourMapper;

@Component
public class FileHandler {

    private final String inputFilePath;
    private final List<String> fileContent = new ArrayList<>();

    @Autowired
    public FileHandler(@Value("${input.csv.file}") String inputfile) {
        this.inputFilePath= inputfile;

    }

    @PostConstruct
    public void readFileContent() {
    	this.fileContent.clear();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
        }catch (IOException e) {
            throw new CsvFileException("unable to access csv file", e);
        } 
    }

    public List<String> getFileContent() {
        return this.fileContent;
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
			bw.write(person.getAddress().getZipCode());
			bw.write(" ");
			bw.write(person.getAddress().getCity());
			bw.write(", ");
			bw.write(IdToColourMapper.getId(person.getColour()));
			bw.newLine();
			bw.close();
			readFileContent();
		} catch (IOException e) {
            throw new CsvFileException("unable to access csv file", e);
        } 
		
    }
}
