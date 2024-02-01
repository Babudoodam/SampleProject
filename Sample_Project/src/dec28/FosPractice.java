package dec28;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class FosPractice {

	public static void main(String[] args) throws Throwable{

		FileInputStream fi = new FileInputStream("D:/Testing/Dependencies and Material for Live Project/Excel Files/Sample1.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		Sheet ws  = wb.getSheet("Employee");
		int rc = ws.getLastRowNum();
		System.out.println(rc);

		for (int i = 1; i <=rc ; i++) {
			if (ws.getRow(i).getCell(3).getCellType()==CellType.NUMERIC) {
				ws.getRow(i).createCell(4).setCellValue("Pass");

				CellStyle cs = wb.createCellStyle();
				Font font = wb.createFont();
				font.setBold(true);
				font.setColor(IndexedColors.GREEN.getIndex());
				cs.setFont(font);
				ws.getRow(i).getCell(4).setCellStyle(cs);
			}
			else 
			{
				ws.getRow(i).createCell(4).setCellValue("Fail");

				CellStyle cs1 = wb.createCellStyle();
				Font f1 =wb.createFont();
				f1.setBold(true);
				f1.setColor(IndexedColors.RED.getIndex());
				cs1.setFont(f1);
				ws.getRow(i).getCell(4).setCellStyle(cs1);
			}
			
		}
		
		fi.close();
		FileOutputStream fo = new FileOutputStream("D:/Testing/Dependencies and Material for Live Project/Result Excel Files/FOS.xlsx");
		wb.write(fo);
		fo.close();
		wb.close();
	}

}
