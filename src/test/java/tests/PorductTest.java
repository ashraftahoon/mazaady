package tests;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import data.JsonDataReader;
import pages.LoginPage;
import pages.ProductPage;

public class PorductTest extends TestBase{

    ProductPage productPageObject;
	LoginPage loginPageObject;

	@Test(priority = 1)
	public void testLogin() throws FileNotFoundException, IOException, ParseException {
		
		loginPageObject=new LoginPage(driver);
		
		for (int i = 0; i < jsonData.size()-1; i++) {

			JSONObject person= (JSONObject) jsonData.get(i);
			String email= (String) person.get("email");
			String password= (String) person.get("password");
			String type= (String) person.get("type");
			String message= (String) person.get("message");
			loginPageObject.doLoginScenarios(email, password,type,message);

		}
	}
	
	@Test(priority = 2)
	public void testProductPage() throws AWTException {

		productPageObject=homePageObject.openProductPage();
		assertion.assertEquals(driver.getCurrentUrl(),"https://staging.mazaady.com/add-product");
		
		for (int i = 0; i < jsonData.size(); i++) {
			JSONObject productRequiredData= (JSONObject) jsonData.get(3);
			String auctionName= (String) productRequiredData.get("auctionName1");
			String autoSearch1= (String) productRequiredData.get("autoSearch1");
			String autoSearch2= (String) productRequiredData.get("autoSearch2");
			String count= (String) productRequiredData.get("count");
			String text1= (String) productRequiredData.get("text1");
			String text2= (String) productRequiredData.get("text2");
			String buyValue= (String) productRequiredData.get("buyValue");
			String bidValue= (String) productRequiredData.get("bidValue");
			String estimateValue= (String) productRequiredData.get("estimateValue");
			productPageObject.fillProductDetails(auctionName, autoSearch1, autoSearch2, count,text1,text2, buyValue, bidValue, estimateValue);
		}
		
	}
}
