package week5.day1assignment;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public static String[][] readdata(String Sheetname) throws IOException {
		// TODO Auto-generated method stub
		
		//open the work both with the path
XSSFWorkbook wb=new XSSFWorkbook("./Data/ExcelData.xlsx");
//open the worksheet
XSSFSheet ws=wb.getSheet(Sheetname);
int rowcount=ws.getLastRowNum();
int columncount=ws.getRow(0).getLastCellNum();
String[][] data =new String[rowcount][columncount];
for(int i=1;i<=rowcount;i++) {
	for(int j=0;j<columncount;j++) {
		String text=ws.getRow(i).getCell(j).getStringCellValue();
		System.out.println(text);
		data[i-1][j]=text;
	}
}
//close the worksheet
wb.close();
return data;

	}

}
