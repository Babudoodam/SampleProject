package jan2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PracticeExtentReports1 {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	@BeforeTest
	public void startReport() {
		report = new ExtentReports("./ExtentReports/demo1.html");
	}
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	@Test
	public void testPass() {
		test = report.startTest("Pass Test");
		test.assignAuthor("Babu");
		test.assignCategory("Functional");
		driver.get("https://google.com/");
		String Expected = "Google";
		String Actual = driver.getTitle();
		if(Actual.equalsIgnoreCase(Expected)) {
			test.log(LogStatus.PASS, "Title is Match");
		}
		else {
			test.log(LogStatus.FAIL, "Tile is Mismatch");
		}
	}
	
	@Test
	public void testFail() {
		test = report.startTest("Fail Test");
		test.assignAuthor("Babu1");
		test.assignCategory("Functional");
		driver.navigate().to("https:/www.gmail.com/");
		String Expected = "Google";
		String Actual = driver.getTitle();
		if(Actual.equalsIgnoreCase(Expected)) {
			test.log(LogStatus.PASS, "Title is Match");
		}
		else {
			test.log(LogStatus.FAIL, "Tile is Mismatch");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}
	
}
