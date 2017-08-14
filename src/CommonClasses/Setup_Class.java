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

	private static final long DEFAULT_FIND_ELEMENT_TIMEOUT = 30;
	 
	public WebDriver driver;

	By currentTab = By.xpath("//*[@class = 'current']");
	By nextButton = By.xpath("//*[text() = 'Next']");
	By headerText = By.xpath("//*[@class = 'title-card']");

	public WebDriver setup() throws Exception {

		System.setProperty("webdriver.chrome.driver","//Users//navpreetsingh//Downloads//Office Data//JARS//chromedriver");
		
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

	public void getRentalsURL() {
		driver.get("http://qarentals.shuttl.com/");
	}

	public String getCurrentTab() {
		String tabName = driver.findElement(currentTab).getText();
		return tabName;
	}

	public void clickNextButton() {
			driver.findElement(nextButton).click();
	}

	public void printHeaderText() {
		String Text = driver.findElement(headerText).getText();
		System.out.println("Text on the header of page = " + Text);
	}

	
	public void selectFromAutosuggestion(WebElement ele,Keys key, Keys key1)
	{
		WebElement act = ele;
		act.sendKeys(key);
		act.sendKeys(key1);
		
	}
	
	
	
	public static void captureScreenShot(WebDriver ldriver, String name) throws Exception{
		         
		 File src=((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);           
		FileUtils.copyFile(src, new File("Screenshots//"+name+System.currentTimeMillis()+".png"));      
		
}	
	

	public void click(By locator, long... waitSeconds){
			WebElement we = getElementWhenVisible(locator, waitSeconds);
			we.click();
		}
	    
	 public WebElement getElementWhenVisible(By locater, long... waitSeconds)
		{
			assert waitSeconds.length <= 1;
			long seconds = waitSeconds.length > 0 ? waitSeconds[0] : DEFAULT_FIND_ELEMENT_TIMEOUT;

			WebElement element = null;
			WebDriverWait wait  = new WebDriverWait(driver, seconds);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
			return element;
		}
	
	 public void click(WebElement we){
			we.click();
		}
		
	 
	 // Method to connect with SQL
	 
		public String connectSQL(String ip, String username, String password, String dbName, String query, String columnName) throws Exception
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is loaded");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://"+ip+"/"+dbName+"",username,password);
			System.out.println("Connected to mysql database");
			
			Statement smt = con.createStatement();
		
			ResultSet res = smt.executeQuery(query);
			String value = "";
			
			while(res.next())
			{
				value = res.getString(columnName);  // specify column name
				System.out.println("Data fetched from database = "+value);
					
			}
				
			return value;
			
		}		
		
	 
	 
}
