package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SourceCity {

	public WebDriver driver;

	By sourceCities = By.xpath("//*[@class = 'source-city-list-item']");

	public SourceCity(WebDriver driver) {
		this.driver = driver;
	}

	public void printSourceCities() {
		List<WebElement> cities = driver.findElements(sourceCities);

		System.out.println("Count of number of source cities = " + cities.size());
		System.out.println("Name of the cities are =");

		for (int i = 0; i < cities.size(); i++)

		{
			System.out.println(cities.get(i).getText());

		}

	}

	public void selectSourceCity(String sourceLoc) {

		List<WebElement> cities = driver.findElements(sourceCities);

		for (int i = 0; i < cities.size(); i++)

		{
			if (cities.get(i).getText().contains(sourceLoc)) {
				cities.get(i).click();
				break;
			}

		}
	}


}
