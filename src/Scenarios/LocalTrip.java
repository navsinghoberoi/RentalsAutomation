package Scenarios;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import CommonClasses.Setup_Class;
import Pages.Contact_Details;
import Pages.Homepage;
import Pages.Pickup;
import Pages.SeatingCapacity;
import Pages.SourceCity;
import Pages.TripLocations;
import Pages.ViewFare;

public class LocalTrip {

	public static WebDriver driver;

	@Test
	public void makeLocalTrip() throws Exception {

		System.out.println("Scenario -- Make a local trip");

		Setup_Class set = new Setup_Class(driver);

		driver = set.setup(); // Open browser

		Properties prop = set.loadPropertyFile();

		String URL = prop.getProperty("rentalsURL"); // Open Rentals URL

		driver.get(URL); // Open Rentals URL

		Homepage homeObj = new Homepage(driver);

		homeObj.getSizePriceButton();
		homeObj.clickPriceButton(); // Click Get Price button

		SourceCity sourceObj = new SourceCity(driver);
		sourceObj.printSourceCities();

		// Source City
		Thread.sleep(5000);
		/*String tab1 = set.getCurrentTab();
		System.out.println("Name of the tab on Source City page = " + tab1);*/

		String SourceCity = prop.getProperty("sourceCity");
		sourceObj.selectSourceCity(SourceCity);

		// Pickup Locations
		Pickup pickObj = new Pickup(driver);

		String pickupLocation = prop.getProperty("pickupPoint");
		pickObj.enterPickupLocation(pickupLocation);
		Thread.sleep(3000);

		/*String tab2 = set.getCurrentTab();
		System.out.println("Name of the tab on Pickup Location page = " + tab2);*/

		set.selectFromAutosuggestion(driver.findElement(pickObj.pickupLocation), Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(5000);

		// Trip Locations
		TripLocations tripObj = new TripLocations(driver);

		/*String tab3 = set.getCurrentTab();
		System.out.println("Name of the tab on Trip Locations page = " + tab3);*/

		int size = tripObj.sizeOfTourCities();
		System.out.println("Count of number of tour cities = " + size);

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(size, 2);
		System.out.println("Assertion of number of cities on Trip locations is done");

		boolean checkPickupOnTrip = tripObj.isCityOnTripPage(pickupLocation);
		System.out.println("Is pickup point displayed on Trip locations page ? " + checkPickupOnTrip);
		sa.assertEquals(checkPickupOnTrip, true);

		boolean NewCityButton = tripObj.isAddButtonDisplayed();
		System.out.println("Is Add New City point displayed on Trip locations page ? " + NewCityButton);
		sa.assertEquals(NewCityButton, true);

		set.clickNextButton();
		Thread.sleep(3000);

		// Seating Capacity
		SeatingCapacity seatObj = new SeatingCapacity(driver);

		/*String tab4 = set.getCurrentTab();
		System.out.println("Name of the tab on Seating Capacity page = " + tab4);*/

		String Seatcapacity = prop.getProperty("Seater");
		seatObj.selectSeatingCapacity(Seatcapacity);
		set.clickNextButton();

		Thread.sleep(3000);

		ViewFare fareObj = new ViewFare(driver);

		/*String tab5 = set.getCurrentTab();
		System.out.println("Name of the tab on View Fare page = " + tab5);*/

		String numOfDays = prop.getProperty("tripDays"); // Change number of Days from dropdown
		fareObj.numOfDays(numOfDays);
		Thread.sleep(2000);
		fareObj.fareBreakup(); // Click on Fare breakup button
		Thread.sleep(2000);

		String trip = prop.getProperty("tripType");

		boolean localTrip = fareObj.printKeys(trip);
		System.out.println("Result of assertion of trip type = " + localTrip);
		sa.assertEquals(localTrip, true);

		String cityOnViewFare = prop.getProperty("sourceCity"); // Should be same as Source City

		boolean cityName = fareObj.printValues(cityOnViewFare);
		System.out.println("Result of assertion of source city name = " + cityName);
		sa.assertEquals(cityName, true);

		String Ldistance = prop.getProperty("distance");
		boolean tripDistance = fareObj.printValues(Ldistance);
		System.out.println("Result of assertion of trip distance boolean = " + tripDistance);
		sa.assertEquals(tripDistance, true);

		fareObj.requestNow();
		Thread.sleep(5000);

		Contact_Details contactObj = new Contact_Details(driver);

		String name = prop.getProperty("userName") + System.currentTimeMillis(); // To make unique name everytime
		contactObj.enterName(name);
		Thread.sleep(1000);
		String number = prop.getProperty("userNumber");
		contactObj.phoneNum(number);
		Thread.sleep(1000);
		String email = prop.getProperty("userEmail");
		contactObj.email(email);
		Thread.sleep(1000);
		contactObj.selectTripStartDate();
		String comments = prop.getProperty("userComments");
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
		String dbValue = set.connectSQL(RentalsDB_IP, RentalsDB_Username, RentalsDB_Pwd, RentalsDB_DBName, RentalsDB_Query, RentalsDB_Column);
		Thread.sleep(8000);
		
		sa.assertEquals(dbValue, name);
		System.out.println("Assertion of name from DB is done");
		
		sa.assertAll();
		
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				Setup_Class.captureScreenShot(driver, "failScreenshot");
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