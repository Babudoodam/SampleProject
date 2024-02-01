package dec27;

import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FosPractice {

	public static void main(String[] args) throws Throwable {
		String eid="";
		
		FileInputStream fi = new FileInputStream("D:/Testing/Dependencies and Material for Live Project/Excel Files/Sample1.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheet("Employee");
		int rows = ws.getLastRowNum();
		System.out.println("No of rows are:"+rows);
		
		for (int i = 1; i <=rows; i++) {
			String fname=ws.getRow(i).getCell(0).getStringCellValue();
			String mname = ws.getRow(i).getCell(1).getStringCellValue();
			String lname = ws.getRow(i).getCell(2).getStringCellValue();
			
			if (ws.getRow(i).getCell(3).getCellType()==CellType.NUMERIC) {
				int celldata = (int) ws.getRow(i).getCell(3).getNumericCellValue();
				eid = String.valueOf(celldata);
				ws.getRow(i).createCell(4).setCellValue("Pass");
			}
			
			else {
				eid = ws.getRow(i).getCell(3).getStringCellValue();
				ws.getRow(i).createCell(4).setCellValue("Fail");
			}
			
			System.out.println(fname+"                "+mname+"               "+lname+"                     "+eid); 
		}
		
		fi.close();
		FileOutputStream fo = new FileOutputStream("D:/Testing/Dependencies and Material for Live Project/Result Excel Files/Sample.xlsx");
		wb.write(fo);
		fo.close();
		wb.close();
	}

}
