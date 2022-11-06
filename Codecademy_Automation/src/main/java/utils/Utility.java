package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
		
	public static void captureScreen(int testID, WebDriver driver) throws IOException {
		
        TakesScreenshot ts =(TakesScreenshot)driver;
		
	    LocalDateTime myObj = LocalDateTime.now();
	    
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH.mm.ss");
	    String formattedDate = myObj.format(myFormatObj);
	    
	    File src= ts.getScreenshotAs(OutputType.FILE);
		
		File dest= new File("C:\\Users\\Akshay Shelke\\OneDrive\\Desktop\\Automation Screenshots\\"+testID+".png"+formattedDate+".jpg");
		
		FileHandler.copy(src,dest);
	}
	

	public static String getDataFromExcelSheet(String sheetname, int rowNumber, int cellNumber) throws EncryptedDocumentException, IOException {
		
		String path= "C:\\Users\\Akshay Shelke\\OneDrive\\Desktop\\Test cases proper format.xlsx";
			
		FileInputStream file = new FileInputStream(path);
		
        Workbook wb= WorkbookFactory.create(file);
		
		Sheet s = wb.getSheet(sheetname);
		
		Row r= s.getRow(rowNumber);
		
		Cell c = r.getCell(cellNumber);
		
		String data="";

		try {
			data= c.getStringCellValue();
		}
		catch(IllegalStateException e) {
			
			double value= c.getNumericCellValue();
			data=Double.toString(value);  
		}
		catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		return data;
	}
}
