package Pages;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonClasses.Setup_Class;

public class Basepage extends Setup_Class {

	public Basepage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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
