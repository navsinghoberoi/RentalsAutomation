package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ViewFare {

	public WebDriver driver;
	
	By seeRoute = By.xpath("(//*[text() = '+ See Route'])[2]");
	By fareBreakup = By.xpath("//*[text() = '+ Fare Breakup']");
	By requestNow = By.xpath("//*[text() = 'Request Now']");
	By numOfDays = By.xpath("//*[@class = 'dropdown-menu rounded']");
	By pushbackSeatsRadioButton = By.xpath("//*[@class = 'onoffswitch-label' and @for = 1]");
	By acRadioButton = By.xpath("//*[@class = 'onoffswitch-label' and @for = 2]");
	By lcdTVRadioButton = By.xpath("//*[@class = 'onoffswitch-label' and @for = 3]");
	
	By keys = By.xpath("//*[@class = 'col s8']");
	By values = By.xpath("//*[@class = 'col s4']");
	
	
	public ViewFare(WebDriver driver) {
		this.driver = driver;
	}
	
	public void seeRoute()
	{
		boolean ab = driver.findElement(seeRoute).isDisplayed();
		if(ab == true)
		{
			System.out.println("See route option is displayed");
			driver.findElement(seeRoute).click();
		}		
			
		else
		{
			System.out.println("See route option is displayed");
		}	
		
		
	}
	
	
	public void fareBreakup()
	{
		driver.findElement(fareBreakup).click();
	}
	
	public void requestNow()
	{
		driver.findElement(requestNow).click();
	}
	
	public void numOfDays(String days)
	{
		Select sel = new Select(driver.findElement(numOfDays));
		sel.selectByVisibleText(days);
	}
	
	
	public void togglePushbackSeatsRadioButton()
	{
		driver.findElement(pushbackSeatsRadioButton).click();
	}
	
	public void toggleACRadioButton()
	{
		driver.findElement(acRadioButton).click();
	}
	
	public void togglLCDTVRadioButton()
	{
		driver.findElement(lcdTVRadioButton).click();
	}
	
	
	public boolean printKeys(String data)
	{
		boolean keyBool = false;
		List<WebElement> key = driver.findElements(keys);
		
		System.out.println("keys are -");
		
		for(int i=0; i<key.size(); i++ )
		{
			System.out.println(key.get(i).getText());
		}
		
		for(int i=0; i<key.size(); i++ )
		{
			if(key.get(i).getText().equalsIgnoreCase(data))

			{	keyBool = true;
				break;
			}
			
			else
			{	keyBool = false; }	
			
		}	
		
		return keyBool;
	}
	

	public boolean printValues(String data)
	{
		boolean valBool = false;
		List<WebElement> value = driver.findElements(values);	
		
		System.out.println("Values are -");
		
		for(int i=0; i<value.size(); i++ )
		{
			System.out.println(value.get(i).getText());
		}
		
		for(int i=0; i<value.size(); i++ )
		{
			if(value.get(i).getText().equalsIgnoreCase(data))

			{	valBool = true;
				break;
			}
			
			else
			{	valBool = false; }	
			
		}	
		
		return valBool;
		
	}
	
	
	
	
	
	
}
