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

import de.accesor.models.Person;
import de.accesor.util.IdToColourMapper;

@Component
public class FileHandler {

    private final String inputFilePath;
    private final List<String> fileContent = new ArrayList<>();

    @Autowired
    public FileHandler(@Value("${input.csv.file}") String inputfile) {
        this.inputFilePath= inputfile;

    }

    @PostConstruct
    public void readFileContent() throws IOException {
    	this.fileContent.clear();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
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
			bw.write(person.getZipCode());
			bw.write(" ");
			bw.write(person.getCity());
			bw.write(", ");
			new IdToColourMapper();
			bw.write(IdToColourMapper.getId(person.getColour()));
			bw.newLine();
			bw.close();
			readFileContent();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
    }
}
