package CommonClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Setup_Class {

	protected static final long DEFAULT_FIND_ELEMENT_TIMEOUT = 30;
	 
	public WebDriver driver;

	protected By currentTab = By.xpath("//*[@class = 'current']");
	protected By nextButton = By.xpath("//*[text() = 'Next']");
	protected By headerText = By.xpath("//*[@class = 'title-card']");

	public WebDriver setup() throws Exception {

		System.setProperty("webdriver.chrome.driver","//Users//nitish//Downloads//chromedriver");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;

	}

	
	// Method to run on Saucelabs
	
	/*public WebDriver setup() throws Exception {

		String USERNAME = "saucelabsAuto1";
		String ACCESS_KEY = "f55fae40-2148-4eb9-9b7a-845b6120368d";
	    String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	    System.out.println(URL);
		  
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 8.1");
		caps.setCapability("version", "59.0");
		 
		WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return driver;
	
}	*/


	  public Properties loadPropertyFile() throws Exception
	  { FileInputStream
	  fileInput = new FileInputStream(new File("Resources//Config.properties"));
	  Properties prop = new Properties(); prop.load(fileInput);
	  
	  return prop; 
	  }
	  


	public Setup_Class(WebDriver driver)

	{
		this.driver = driver;
	}

	 
}
