package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CommonClasses.Setup_Class;

public class Contact_Details {

	public WebDriver driver;
	
	Setup_Class setObj = new Setup_Class(driver);
	
	By name = By.xpath("//*[@placeholder = 'Name']");
	By phoneNum = By.xpath("//*[@placeholder = 'Mobile Number']");
	By email = By.xpath("//*[@placeholder = 'Email']");
	By calendar = By.xpath("//*[@placeholder = 'Trip Start Date']");
	By nextMonth = By.xpath("//*[contains(@class,'navigation--next')]");
	By selectDate = By.xpath("//*[@aria-label = 'day-10']");
	By comments = By.xpath("//*[@placeholder = 'Additional Comments']");
	By submitButton = By.xpath("//*[text() = 'Submit Request']");
	By submittedText = By.xpath("//*[text() = 'Request Submitted Successfully']");
	
	
	
	public Contact_Details(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterName(String name1)
	{
		driver.findElement(name).sendKeys(name1);
	}
	
	
	
	public void phoneNum(String num)
	{
		driver.findElement(phoneNum).sendKeys(num);
	}
	
	public void email(String mail)
	{
		driver.findElement(email).sendKeys(mail);
	}
	
	public void selectTripStartDate() throws Exception
	{
		driver.findElement(calendar).click();
		Thread.sleep(1000);
		driver.findElement(nextMonth).click();
		Thread.sleep(1000);
		driver.findElement(selectDate).click();
		
	}
	
	
	public void addComments(String data)
	{
		driver.findElement(comments).sendKeys(data);
	}
	
	
	
	public void submitRequest()
	{
		driver.findElement(submitButton).click();
	}
	
	
	public String getSubmittedText()
	{
		String data = driver.findElement(submittedText).getText();
		return data;
	}
	
	
	
}
