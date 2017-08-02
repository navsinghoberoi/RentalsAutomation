package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TripLocations {

	public WebDriver driver;

	By NewCity = By.xpath("//*[text() = '+ Add Next City']");
	public By DestinationLoc = By.xpath("//*[@placeholder = 'Search for Destination Locations']");
	By tourCities = By.xpath("//*[@class = 'city']");

	public TripLocations(WebDriver driver) {
		this.driver = driver;
	}

	public void addNewCity() {
		driver.findElement(NewCity).click();
	}

	public void enterDestinationLoc(String toLoc) {
		driver.findElement(DestinationLoc).sendKeys(toLoc);
	}

	public int sizeOfTourCities() {

		List<WebElement> Cities = driver.findElements(tourCities);
	
		int size = Cities.size();
		System.out.println("Name of the tour cities are =");
	
		for (int i = 0; i < Cities.size(); i++)
		{
			System.out.println(Cities.get(i).getText());
		}

		return size;
	}


	public boolean isCityOnTripPage(String city)
	{
		boolean valPickup = false;
		List<WebElement> Cities = driver.findElements(tourCities);
		
		for (int i = 0; i < Cities.size(); i++)
		{
			if(Cities.get(i).getText().contains(city))

			{	valPickup = true;
				break;					}
			
			else
				{	valPickup = false;
										}	
			
		}
		
		return valPickup;

	}

	
	public boolean isAddButtonDisplayed()
	{
		boolean valDisplayed = driver.findElement(NewCity).isDisplayed();
		return valDisplayed;
	}
	
}
