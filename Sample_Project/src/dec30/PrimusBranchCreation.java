package dec30;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PrimusBranchCreation {
	
	WebDriver driver;
	Properties pro = new Properties();
	
	@BeforeTest
	public void startUp() throws Throwable{
		
		pro.load(new FileInputStream("D:/Testing/Live_Project/Sample_Project/ObjectRepository/PrimusLocator.properties"));
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(pro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath(pro.getProperty("ObjUser"))).sendKeys(pro.getProperty("Username"));
		driver.findElement(By.xpath(pro.getProperty("ObjPass"))).sendKeys(pro.getProperty("Password"));
		driver.findElement(By.xpath(pro.getProperty("ObjLogin"))).click();
		
	}
	
	@Test
	public void newBranchCreation()throws Throwable {
		
		pro.load(new FileInputStream("D:/Testing/Live_Project/Sample_Project/ObjectRepository/PrimusLocator.properties"));
		
		driver.findElement(By.xpath(pro.getProperty("ObjBranches"))).click();
		driver.findElement(By.xpath(pro.getProperty("ObjNewBrach"))).click();
		
		driver.findElement(By.xpath(pro.getProperty("ObjBranchname"))).sendKeys(pro.getProperty("Branchname"));
		driver.findElement(By.xpath(pro.getProperty("ObjAddress1"))).sendKeys(pro.getProperty("Address1"));
		driver.findElement(By.xpath(pro.getProperty("ObjAddress2"))).sendKeys(pro.getProperty("Address2"));
		driver.findElement(By.xpath(pro.getProperty("ObjAddress3"))).sendKeys(pro.getProperty("Address3"));
		driver.findElement(By.xpath(pro.getProperty("ObjArea"))).sendKeys(pro.getProperty("Area"));
		driver.findElement(By.xpath(pro.getProperty("ObjZipCode"))).sendKeys(pro.getProperty("Zipcode"));
		new Select(driver.findElement(By.xpath(pro.getProperty("ObjCountry")))).selectByVisibleText(pro.getProperty("Country"));
		new Select(driver.findElement(By.xpath(pro.getProperty("ObjState")))).selectByVisibleText(pro.getProperty("State"));
		new Select(driver.findElement(By.xpath(pro.getProperty("ObjCity")))).selectByVisibleText(pro.getProperty("City"));
		driver.findElement(By.xpath(pro.getProperty("ObjSubmit"))).click();
		
		String message = driver.switchTo().alert().getText();
		Reporter.log(message,true);
		driver.switchTo().alert().accept();
		
		driver.findElement(By.xpath(pro.getProperty("ObjLogout"))).click();
	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
