package de.accesor.util;

import java.util.HashMap;
import java.util.Map;

public class IdToColourMapper {

    public IdToColourMapper() {

    }

	private static final Map<String, String> idToColour = new HashMap<>() {
		{
			put("1", "blau");
			put("2", "grün");
			put("3", "violett");
			put("4", "rot");
			put("5", "gelb");
			put("6", "türkis");
			put("7", "weiß");

		}
	};

    private static Map<String, String> colourToId = new HashMap<>() {
        {
            put("blau", "1");
            put("grün", "2");
            put("violett", "3");
            put("rot", "4");
            put("gelb", "5");
            put("türkis", "6");
            put("weiß", "7");

        }
    };

	public static String getColour(String id) {
		return idToColour.get(id.trim());
	}



	public static String getId(String colour) {
		return colourToId.get(colour);
	}

}
