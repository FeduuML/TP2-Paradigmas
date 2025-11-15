package ppunlam;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LectorJSON {
	public static JSONArray cargarArray(String path) {
		try {
			JSONParser parser = new JSONParser();
			return (JSONArray) parser.parse(new FileReader(path));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
