package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends PageBase{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private  static final By profilePic= By.xpath("//img[@alt=\"user\"]");
	private  static final By addProduct= By.xpath("//div[@class=\"dropdown-menu dropdown-menu-left\"]//ul[@class=\"categories-sections\"]//li[5]");

	public ProductPage openProductPage() {

		Actions action= new Actions(driver);
		action
		.moveToElement(find(profilePic))
		.moveToElement(find(addProduct))
		.click()
		.build()
		.perform();
		return new ProductPage(driver);
	}
}
