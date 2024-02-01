package dec26;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileInputStreamPractice {

	public static void main(String[] args) throws Throwable {
		
		FileInputStream fi = new FileInputStream("D:/Testing/Dependencies and Material for Live Project/Excel Files/Sample1.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet("Employee");
		XSSFRow row = ws.getRow(0);
		int tr = ws.getLastRowNum();
		int tc = row.getLastCellNum();
		System.out.println("No.of Rows in a sheet is::"+tr+"                       "+"No.of Columns in first row is:"+tc  );
		//2Row FirstName MiddleName LastName eId 
		XSSFRow row2 = ws.getRow(2);
		XSSFCell c1 = row2.getCell(0);
		XSSFCell c2 = row2.getCell(1);
		XSSFCell c3 = row2.getCell(2);
		XSSFCell c4 = row2.getCell(3); 
		
		String Firstname = c1.getStringCellValue();
		String Middlename = c2.getStringCellValue();
		String Lastname = c3.getStringCellValue();
		int Employeeid = (int) c4.getNumericCellValue();
		
		System.out.println(Firstname+"            "+Middlename+"                     "+Lastname+"                           "+Employeeid);
		fi.close();
		wb.close();
	}
}
