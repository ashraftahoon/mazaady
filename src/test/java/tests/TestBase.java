package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import data.JsonDataReader;
import io.ous.jtoml.ParseException;
import pages.HomePage;

public class TestBase {

	public  WebDriver driver;
	public SoftAssert assertion;
	public HomePage homePageObject;
	public JSONArray jsonData;
	public JsonDataReader jsonReader;
	@BeforeTest
	public  void  setUP() throws FileNotFoundException, IOException, ParseException, Exception {
		driver = new ChromeDriver();
		driver.get(" https://staging.mazaady.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		homePageObject=new HomePage(driver);
		assertion = new SoftAssert() ;
		jsonReader=new JsonDataReader("/src/test/java/data/UserData.json");
		jsonData=jsonReader.getJsonArray(); 
	}

	   
	@AfterTest

	public void tearDown() {
		//	driver.quit();
	}
}
