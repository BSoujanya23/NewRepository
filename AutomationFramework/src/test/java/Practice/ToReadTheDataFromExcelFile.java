package Practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadTheDataFromExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Create an object of FileInputStream
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestData (2).xlsx");
		//Create an object of excel file
		Workbook wb = WorkbookFactory.create(fis);
		//call the method
		String lastname = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
		System.out.println(lastname);

	}

}

