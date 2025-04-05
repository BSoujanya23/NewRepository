package DDTWithUtilities;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;

public class Scenario2 {

	public static void main(String[] args) throws IOException {
		//To Read the data from property file
		PropertyFileUtility putil=new PropertyFileUtility();
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To Read the data from Excel file
		ExcelFileUtility eutil=new ExcelFileUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		
		
		//Step1: To Launch the Browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		
		//Step2: Login With Valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step3: Click on Organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step4: Click on create organization look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step5: Fill the mandatory fields
		Random r=new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+random);
		
		//Step6: Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(name.contains(ORGNAME+random)) {
			System.out.println(name+" Added Successfully");
		}else {
			System.out.println(name+" Failed to Add");
		}
		
		//Step7: To Logout
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step8: To close the Browser
		driver.quit();
		
	}

}
