package dec26;

import java.io.FileInputStream;



import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FISPractice {

	public static void main(String[] args)throws Throwable {
		FileInputStream fi = new FileInputStream("D:/Testing/Dependencies and Material for Live Project/Excel Files/Sample1.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet("Employee");
		int cc3 = (int) ws.getRow(6).getCell(3).getNumericCellValue();
		System.out.println(cc3);
		
		String c3=ws.getRow(3).getCell(3).getStringCellValue();
		System.out.println(c3);
		fi.close();
		wb.close();	
		
	}

}
