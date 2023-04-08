package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class JsonDataReader {
	private String filePath;
	private File srcFile;

	public JsonDataReader(String jsonFilePath) {
		filePath = System.getProperty("user.dir") + jsonFilePath;
		srcFile = new File(filePath);
	}

	public JSONArray getJsonArray() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		JSONParser parser=new JSONParser();
		return (JSONArray) parser.parse(new FileReader(srcFile));
	}
}
