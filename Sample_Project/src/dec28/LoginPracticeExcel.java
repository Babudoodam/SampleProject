package dec28;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
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


public class LoginPracticeExcel  {

	WebDriver driver;
	Properties conpro = new Properties();

	@BeforeTest
	public void setUp()throws Throwable {

		conpro.load(new FileInputStream("D:/Testing/Live_Project/Sample_Project/ObjectRepository/Locators.properties"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void login() throws Throwable{

		FileInputStream fi = new FileInputStream("D:/Testing/Dependencies and Material for Live Project/Excel Files/LoginOrange.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet ws = wb.getSheet("Login");
		int rc = ws.getLastRowNum();
		System.out.println(rc);

		String Expected = "dashboard";
		

		for (int i = 1; i <= rc; i++) {

			driver.get(conpro.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

			String username = ws.getRow(i).getCell(0).getStringCellValue();
			String Password = ws.getRow(i).getCell(1).getStringCellValue();

			driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(username);
			driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(Password);
			
			driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
			String Actual = driver.getCurrentUrl();

			if (Actual.endsWith(Expected)) {
				Reporter.log("Login Success",true);
				ws.getRow(i).createCell(2).setCellValue("Pass");
				
				CellStyle cs1 = wb.createCellStyle();
				Font f1 = wb.createFont();
				f1.setBold(true);
				f1.setColor(IndexedColors.GREEN.getIndex());
				cs1.setFont(f1);
				ws.getRow(i).getCell(2).setCellStyle(cs1);

			}
			else {
				Reporter.log("Login Unsuccess",true);
				ws.getRow(i).createCell(2).setCellValue("Fail");
				
				CellStyle cs2 = wb.createCellStyle();
				Font f2 = wb.createFont();
				f2.setBold(true);
				f2.setColor(IndexedColors.RED.getIndex());
				cs2.setFont(f2);
				ws.getRow(i).getCell(2).setCellStyle(cs2);

			}

		}
		fi.close();
		FileOutputStream fo = new FileOutputStream("D:/Testing/Dependencies and Material for Live Project/Result Excel Files/LoginOrangeResult.xlsx");
		wb.write(fo);
		fo.close();
		wb.close();



	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}


