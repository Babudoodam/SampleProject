package dec30;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Primus {

	public static void main(String[] args) throws Throwable {

		WebDriver dr = new ChromeDriver();
		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		dr.get("http://primusbank.qedgetech.com/");
	

		dr.findElement(By.xpath("//input[@id='txtuId']")).sendKeys("Admin");
		dr.findElement(By.xpath("//input[@id='txtPword']")).sendKeys("Admin");
		dr.findElement(By.xpath("//input[@id='login']")).click();

		String expected="adminflow.aspx";
		String Actual=dr.getCurrentUrl();
		if (Actual.endsWith(expected)) {
			System.out.println("Login successeful");
		}
		else
		{
			String catchText=	dr.switchTo().alert().getText();
			System.out.println(catchText);		
		}

		dr.quit(); 
	}
}
