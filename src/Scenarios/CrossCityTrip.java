package Scenarios;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonClasses.Setup_Class;
import Pages.Basepage;
import Pages.Contact_Details;
import Pages.Homepage;
import Pages.Pickup;
import Pages.SeatingCapacity;
import Pages.SourceCity;
import Pages.TripLocations;
import Pages.ViewFare;

public class CrossCityTrip extends Setup_Class{

	public CrossCityTrip(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	Setup_Class set = new Setup_Class(driver);
	Basepage basepage = new Basepage(driver);
	//public static WebDriver driver;

	@Test
	public void makeLocalTrip() throws Exception {

		System.out.println("Scenario -- Make a cross city trip");

		

		//driver = set.setup(); // Open browser

		Properties prop = set.loadPropertyFile();

		String URL = prop.getProperty("rentalsURL"); // Open Rentals URL

		driver.get(URL); // Open Rentals URL

		Homepage homeObj = new Homepage(driver);

		homeObj.getSizePriceButton();
		homeObj.clickPriceButton(); // Click Get Price button

		SourceCity sourceObj = new SourceCity(driver);
		sourceObj.printSourceCities();

		// Source City
		String tab1 = basepage.getCurrentTab();
		//String tab1 = 
		System.out.println("Name of the tab on Source City page = " + tab1);

		String SourceCity = prop.getProperty("sourceCityCrossCity");
		sourceObj.selectSourceCity(SourceCity);

		// Pickup Locations
		Pickup pickObj = new Pickup(driver);

		String pickupLocation = prop.getProperty("pickupPointCrossCity");
		pickObj.enterPickupLocation(pickupLocation);
		Thread.sleep(3000);

		String tab2 = basepage.getCurrentTab();
		System.out.println("Name of the tab on Pickup Location page = " + tab2);

		basepage.selectFromAutosuggestion(driver.findElement(pickObj.pickupLocation), Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(5000);

		// Trip Locations
		TripLocations tripObj = new TripLocations(driver);

		String tab3 = basepage.getCurrentTab();
		System.out.println("Name of the tab on Trip Locations page = " + tab3);

		int size = tripObj.sizeOfTourCities();
		System.out.println("Count of number of tour cities before adding new city = " + size);

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(size, 2);

		String newCity = prop.getProperty("newCity");
		System.out.println("Name of the city to be added = "+newCity);
		tripObj.addNewCity();
		Thread.sleep(2000);
		tripObj.enterDestinationLoc(newCity);
		Thread.sleep(3000);
		basepage.selectFromAutosuggestion(driver.findElement(tripObj.DestinationLoc), Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(5000);
	
		int size1 = tripObj.sizeOfTourCities();
		System.out.println("Count of number of tour cities after adding new city = " + size1);

		sa.assertEquals(size1, 3);
		
		
		boolean checkPickupOnTrip = tripObj.isCityOnTripPage(pickupLocation);
		System.out.println("Is pickup point displayed on Trip locations page ? " + checkPickupOnTrip);
		sa.assertEquals(checkPickupOnTrip, true);

		boolean checkNewCityOnTrip = tripObj.isCityOnTripPage(newCity);
		System.out.println("Is new city displayed on Trip locations page ? " + checkNewCityOnTrip);
		sa.assertEquals(checkNewCityOnTrip, true);
		
		boolean NewCityButton = tripObj.isAddButtonDisplayed();
		System.out.println("Is Add New City point displayed on Trip locations page ? " + NewCityButton);
		sa.assertEquals(NewCityButton, true);

		basepage.clickNextButton();
		Thread.sleep(3000);

		// Seating Capacity
		SeatingCapacity seatObj = new SeatingCapacity(driver);

		String tab4 = basepage.getCurrentTab();
		System.out.println("Name of the tab on Seating Capacity page = " + tab4);

		String Seatcapacity = prop.getProperty("SeaterCrossCity");
		seatObj.selectSeatingCapacity(Seatcapacity);
		basepage.clickNextButton();

		Thread.sleep(3000);

		ViewFare fareObj = new ViewFare(driver);

		String tab5 = basepage.getCurrentTab();
		System.out.println("Name of the tab on View Fare page = " + tab5);

		String numOfDays = prop.getProperty("tripDaysCrossCity"); // Change number of Days from dropdown
		fareObj.numOfDays(numOfDays);
		Thread.sleep(2000);
		fareObj.fareBreakup(); // Click on Fare breakup button
		Thread.sleep(2000);

		// Click on See Route button
		fareObj.seeRoute();
		Thread.sleep(2000);
		
		String trip = prop.getProperty("tripTypeCrossCity");

		boolean crossTrip = fareObj.printKeys(trip);
		System.out.println("Result of assertion of trip type = " + crossTrip);
		sa.assertEquals(crossTrip, true);

		
	//	boolean cityName1 = fareObj.printValues(SourceCity);   // Check Source City is displayed
	//	System.out.println("Result of assertion of source city name = " + cityName1);
	//	sa.assertEquals(cityName1, true);

	//	String cityOnViewFare2 = prop.getProperty("newCity"); // Check new city is displayed
	//	boolean cityName2 = fareObj.printValues(cityOnViewFare2);
	//	System.out.println("Result of assertion of new city name = " + cityName2);
	//	sa.assertEquals(cityName2, true);
		
		String Crossdistance = prop.getProperty("distanceCrossCity");
		boolean tripDistance = fareObj.printValues(Crossdistance);
		System.out.println("Result of assertion of trip distance boolean = " + tripDistance);
		sa.assertEquals(tripDistance, true);

		fareObj.requestNow();
		Thread.sleep(5000);

		Contact_Details contactObj = new Contact_Details(driver);

		String name = prop.getProperty("userNameCrossCity") + System.currentTimeMillis();
		contactObj.enterName(name);
		Thread.sleep(1000);
		String number = prop.getProperty("userNumberCrossCity");
		contactObj.phoneNum(number);
		Thread.sleep(1000);
		String email = prop.getProperty("userEmailCrossCity");
		contactObj.email(email);
		Thread.sleep(1000);
		contactObj.selectTripStartDate();
		String comments = prop.getProperty("userCommentsCrossCity");
		contactObj.addComments(comments);
		Thread.sleep(1000);
		contactObj.submitRequest();
		Thread.sleep(2000);

		String url = driver.getCurrentUrl();
		System.out.println("URL after submitting request = " + url);
		String expected = prop.getProperty("requestSubmittedURL");
		sa.assertEquals(url, expected);

		
		String submitText = contactObj.getSubmittedText();
		System.out.println("Text after submitting request = " + submitText);
		String expectedText = prop.getProperty("submittedText");
		sa.assertEquals(submitText, expectedText);
		
		// Compare name from Rentals DB
		
		String RentalsDB_IP = prop.getProperty("QARENTALS_IP");
		String RentalsDB_Username = prop.getProperty("QARENTALS_Username");
		String RentalsDB_Pwd = prop.getProperty("QARENTALS_Password");
		String RentalsDB_DBName = prop.getProperty("QARENTALS_DBname");
		String RentalsDB_Query = prop.getProperty("QARENTALS_Query");
		String RentalsDB_Column = prop.getProperty("QARENTALS_ColumnName");
		
		Thread.sleep(5000);
		String dbValue = basepage.connectSQL(RentalsDB_IP, RentalsDB_Username, RentalsDB_Pwd, RentalsDB_DBName, RentalsDB_Query, RentalsDB_Column);
		Thread.sleep(8000);
		
		sa.assertEquals(dbValue, name);
		System.out.println("Assertion of name from DB is done");

		sa.assertAll();
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				basepage.captureScreenShot(driver, "failScreenshot");
				System.out.println("Screenshot taken");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot = " + e.getMessage());
			}
			
			driver.quit();
		}

		else {
			System.out.println("Closing the driver");
			driver.quit();
		}
	}

}