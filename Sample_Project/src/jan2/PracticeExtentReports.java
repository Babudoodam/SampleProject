package jan2;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class PracticeExtentReports {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	
	@BeforeTest
	public void generateReport() {
		report = new ExtentReports("./ExtentReports/demo.html");
	}

	@Test
	public void tset() {
		logger = report.startTest("Babu");
		logger.log(LogStatus.PASS, "Test Pass");
		System.out.println("Hello World");
		
	}
	@AfterTest
	public void tearDown() {
		report.endTest(logger);
		report.flush();
	}
}
