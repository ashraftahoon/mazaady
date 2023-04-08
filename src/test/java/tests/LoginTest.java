package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import data.JsonDataReader;
import pages.LoginPage;

public class LoginTest extends TestBase {
  
	LoginPage loginPageObject;
	
	@org.testng.annotations.Test
	public void testLogin() throws FileNotFoundException, IOException, ParseException {
		JsonDataReader jsonReader=new JsonDataReader("/src/test/java/data/UserData.json");
		JSONArray loginData=jsonReader.getJsonArray(); 
		loginPageObject=new LoginPage(driver);
		
		for (int i = 0; i < loginData.size(); i++) {
			
			JSONObject person= (JSONObject) loginData.get(i);
		    String email= (String) person.get("email");
		    String password= (String) person.get("password");
		    String type= (String) person.get("type");
		    String message= (String) person.get("message");
			loginPageObject.doLoginScenarios(email, password,type,message);

		}
	}
}
