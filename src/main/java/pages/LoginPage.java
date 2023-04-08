package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private  static final By tesxtEmail= By.id("email");
	private  static final By textPassword= By.id("password");
	private  static final By buttonLogin=By.xpath("//button[text()='Login']");
	private  static final By labelWorng=By.xpath("//li[text()='These credentials do not match our records.']");
	
	public boolean doLoginScenarios(String email,String password,String type , String message) {
		Type(tesxtEmail, email);
		Type(textPassword, password);
		click(buttonLogin);
		
		switch (type) {
		case "wrong mail":
			  return  
					  find(labelWorng).isDisplayed()
					  && find(labelWorng).getText().equals(message);
		case "wrong password":
			  return find(labelWorng).isDisplayed()
					  && find(labelWorng).getText().equals(message);	  
		default:
			return driver.getCurrentUrl().equals(message);
		}
		}
		
   
}
