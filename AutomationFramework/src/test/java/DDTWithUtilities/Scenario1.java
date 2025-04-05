package DDTWithUtilities;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;

public class Scenario1 {

	public static void main(String[] args) throws IOException {
		//To read the data from properties file
		PropertyFileUtility putil= new PropertyFileUtility();
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To read the data from excel file
		ExcelFileUtility eutil=new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		
		//Step1:To Launch the Browser
	    WebDriver driver=null;
	    if(BROWSER.equals("chrome")) {
	    	driver=new ChromeDriver();
	    }
	    else if(BROWSER.equals("edge")){
	    	driver=new EdgeDriver();
	    }
	    else if(BROWSER.equals("firefox")) {
	    	driver=new FirefoxDriver();
	    }
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    
	    
	    //Step2: Login with valid Credentials
	    driver.get(URL);
	    driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	    driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	    driver.findElement(By.id("submitButton")).click();
	    
	    //step3: Navigate to contact link
	    driver.findElement(By.linkText("Contacts")).click();
	    
	    //Step4: Click on Create Contact look up image
	    driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	    
	    
	    //Step5: Fill the mandatory fields
	    driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
	    
	    //Step6: Save and Verify
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	    
	    String name = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	    if(name.contains(LASTNAME)) {
	    	System.out.println(name+" Added Successfully");
	    }
	    else {
	    	System.out.println(name+" Failed to add");
	    }
	    
	    //Step7: To Logout
	    WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    Actions action=new Actions(driver);
	    action.moveToElement(logout).perform();
	    driver.findElement(By.linkText("Sign Out")).click();
	    
	    //Step8: To close the browser
	    driver.quit();
		
	}

}
