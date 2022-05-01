package de.accesor.mappers;

import java.util.HashMap;
import java.util.Map;

import de.accesor.exceptions.PersonColourMappingException;

public class IdToColourMapper {

    private IdToColourMapper() {}

	private static final Map<String, String> idToColour = new HashMap<>() ;

    private static final Map<String, String> colourToId = new HashMap<>();

    static {
        // initialize id to colour mappings
        idToColour.put("1", "blau");
        idToColour.put("2", "grün");
        idToColour.put("3", "violett");
        idToColour.put("4", "rot");
        idToColour.put("5", "gelb");
        idToColour.put("6", "türkis");
        idToColour.put("7", "weiß");

        colourToId.put("blau", "1");
        colourToId.put("grün", "2");
        colourToId.put("violett", "3");
        colourToId.put("rot", "4");
        colourToId.put("gelb", "5");
        colourToId.put("türkis", "6");
        colourToId.put("weiß", "7");
    }

	public static String getColour(String id) {
        String colour = idToColour.get(id.trim());
        if (colour == null) {
            throw new PersonColourMappingException("Unable to get colour for id "+ id);
        }
        return colour;
	}



	public static String getId(String colour) {
		String id = colourToId.get(colour);

        if (id == null) {
            throw new PersonColourMappingException("Unable to map color " + colour + " to a valid id");
        }
        return id;
	}

}
