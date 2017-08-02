package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Pickup {

	public WebDriver driver;

	public By pickupLocation = By.xpath("//*[@placeholder = 'Search for Locality or Landmark']");

	public Pickup(WebDriver driver) {
		this.driver = driver;
	}

	public void enterPickupLocation(String pickupLoc) {
		driver.findElement(pickupLocation).sendKeys(pickupLoc);
	}

}
