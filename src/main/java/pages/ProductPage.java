package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

public class ProductPage extends PageBase {

	public ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private final By nextButton1=By.xpath("//div[@class=\"w-25 text-center m-auto\"]//descendant::button[contains(text(),'Next')]");
	private final By auctionBadge=By.xpath("//img[@src=\"https://mazaady-test.s3.us-east-2.amazonaws.com/LiveBadge/design_img/djeXyI45rbW8yoqZsp159hSWY2m0TtM46zpKIEty.jpeg\"]//following::span[1]");
	private final By auctionName=By.xpath("//input[@placeholder=\"Auction Name\"]"); 
	private final By mainCategory=By.xpath("//input[@placeholder=\"select category\"]");
	private final By subCategory=By.xpath("//input[@placeholder='select subcategory']"); 
	private final By quantity=By.xpath("//input[@placeholder=\"Quantity\"]"); 
	private final By closeButton=By.xpath("//button[text()=' Close '] ");
	private final By mainImage=By.xpath("//label[text()='Main Image']//following::a[1]"); 
	private final By nextStep2=By.xpath("//div[@id=\"step-2\"]//descendant::button[contains(text(),'Previous')]//following-sibling::button[@class=\"btn btn-buy continue\"]"); 
	private final By sellingTypeSelect= By.xpath("//select[@name=\"selling_type\"]");
	private final By buyNowValueInput=By.xpath("//input[@placeholder=\"Buy Now Value\"]");
	private final By startBidValueInput=By.xpath("//input[@placeholder=\"Starting Bid Value\"]");
	private final By estimationValueInput=By.xpath("//input[@placeholder=\"Estimated Value\"]");
	private final By date=By.id("vue-button-to-open-date");   
	private final By numOfDate=By.xpath("//span[@id=\"mddtp-date__selected\"]//following::span[1]");
	private final By dateButton=By.id("mddtp-date__ok");
	private final By time=By.id("vue-button-to-open-time");
	private final By timeButton=By.id("mddtp-time__ok");
	private final By auctionShowType=By.xpath("//span[text()=\"Live\"]");
	private final By nextStep3=By.xpath("//div[@id=\"step-3\"]//descendant::button[contains(text(),'Previous')]//following-sibling::button"); 
	private final By addButton=By.xpath("//button[contains(text(),'Add')]");
	private final By  addSuccessText=By.xpath("//div[@class=\"swal-overlay swal-overlay--show-modal\"]//child::div[text()='product added successfully']"); 


	// method to add product
	public void fillProductDetails(String auctionName1,String autoSearch1, String autoSearch2,String count,
			String text1,String text2, String buyValue,String bidValue,String estimateValue) throws AWTException  {

		click(nextButton1);
		click(auctionBadge);
		Type(auctionName,auctionName1);
		Type(mainCategory, autoSearch1+Keys.ENTER);

		try {
			Thread.sleep(1000);
			Type(subCategory, autoSearch2+Keys.ENTER);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Type(quantity, count);
		JavascriptExecutor  js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)", "");

		driver.switchTo().frame("tinymce_description_ifr");
		WebElement editable = driver.switchTo().activeElement();
		editable.sendKeys(text1);
		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(closeButton));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", find(closeButton));

		driver.switchTo().frame("tinymce_policy_ifr");
		WebElement editable1= driver.switchTo().activeElement();
		editable1.sendKeys(text2);
		driver.switchTo().defaultContent();

		String imageName="images.png";
		String imagePath=System.getProperty("user.dir")+"\\Uploads\\"+imageName;

		Actions action   = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("//label[text()='Main Image']//following::a[1]"));
		action.moveToElement(we).click().build().perform();



		Robot robot = new Robot();
		// CTR c copy image path
		//robot.delay(1000); // wait for the upload dialog to appear
		StringSelection stringSelection = new StringSelection(imagePath);
		Clipboard clipboard=	Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(2000);

		// CTRL V past image path
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000); // wait for the file path to be entered
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		wait.until(ExpectedConditions.elementToBeClickable(nextStep2));
		action.moveToElement(find(nextStep2)).click().build().perform();		

		Select dropDown=new Select(find(sellingTypeSelect));
		dropDown.selectByVisibleText("ESTIMATION VALUE");

		Type(buyNowValueInput, buyValue);
		Type(startBidValueInput, bidValue);
		Type(estimationValueInput, estimateValue);

		click(date);
		action   = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(numOfDate));
		action.moveToElement(find(numOfDate)).click().build().perform();		
		wait.until(ExpectedConditions.elementToBeClickable(dateButton));
		click(dateButton);

		click(time);
		click(timeButton);

		wait.until(ExpectedConditions.elementToBeClickable(auctionShowType));
		click(auctionShowType);
		click(nextStep3);

		click(addButton);

	}




}
