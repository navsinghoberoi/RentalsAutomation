package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonClasses.Setup_Class;

public class Homepage {

	public WebDriver driver;

	Setup_Class setObj = new Setup_Class(driver);
	
	By homepage = By.xpath("//*[@href = '/sourceCity']");
	
	
	public  Homepage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void getSizePriceButton()
	{

		List<WebElement> buttons = driver.findElements(homepage);
		System.out.println("Number of Price buttons on homepage = "+buttons.size());
		
	}
	
	public void clickPriceButton()
	{
		driver.findElement(homepage).click();
	}
	
/*	public void clickPriceButton()
	{
		setObj.click(homepage, 10);
	}*/
	
	
	
}
