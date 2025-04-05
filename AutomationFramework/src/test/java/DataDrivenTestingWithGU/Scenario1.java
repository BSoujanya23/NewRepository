package DataDrivenTestingWithGU;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;

public class Scenario1 {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//Read the data from property file
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//Read the data from excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
	    //Step1: To Launch the Browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}
		wutil.toMaximize(driver);
		wutil.toWaitForElement(driver);
		
		//Step2: Login to the browser with valid credential
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step3: Navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Ste4: Click on create contact look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step5: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//Step6: Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(name.contains(LASTNAME)) {
			System.out.println(name+" Added Successfully");
		}else {
			System.out.println(name+" failed to add");
		}
		
		//Step7: Logout of Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver,logout );
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step8: Close the Browser
		driver.quit();
	}

}
