package dec30;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidatePrimusLogin {

	WebDriver driver;
	Properties conpro = new Properties();

	@BeforeTest 
	public void  setUp()throws Throwable {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test
	public void login()throws Throwable {
		conpro.load(new FileInputStream("D:/Testing/Live_Project/Sample_Project/ObjectRepository/PrimusLocator.properties"));

		FileInputStream fi = new FileInputStream("D:/Testing/Dependencies and Material for Live Project/Excel Files/PrimusLogin.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet ws = wb.getSheet("Login");
		int rc = ws.getLastRowNum();

		String Expected = "adminflow";
		String Actual = "";

		for (int i = 1; i <=rc; i++) {
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			String Username = ws.getRow(i).getCell(0).getStringCellValue();
			String Password = ws.getRow(i).getCell(1).getStringCellValue();

			try {
				driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(Username);
				driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(Password);
				driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
				 

				Actual = driver.getCurrentUrl();

				if (Actual.contains(Expected)) {

					Reporter.log("Login Success",true);
					ws.getRow(i).createCell(2).setCellValue("Pass");
				} 
				else {

					Reporter.log("Login UnSuccess",true);
					ws.getRow(i).createCell(2).setCellValue("Fail");

				}

			} catch (Throwable exc) {
				System.out.println(exc.getMessage());
			}

		}
		fi.close();
		FileOutputStream fo = new FileOutputStream("D:/Testing/Dependencies and Material for Live Project/Result Excel Files/ResultPrimusLogin.xlsx");
		wb.write(fo);
		fo.close();
		wb.close();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
