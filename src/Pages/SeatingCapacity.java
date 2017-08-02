package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SeatingCapacity {

	public WebDriver driver;

	By seatingDropdown = By.xpath("//*[@class = 'dropdown-menu seating-dropdown']");

	public SeatingCapacity(WebDriver driver) {
		this.driver = driver;
	}

	public void selectSeatingCapacity(String capacity)

	{
		Select sel = new Select(driver.findElement(seatingDropdown));
		sel.selectByVisibleText(capacity);
	}

}
