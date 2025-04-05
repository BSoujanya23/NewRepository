package DataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario2 {

	public static void main(String[] args) throws IOException {
		//To Read the data from properties file
		FileInputStream pfis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(pfis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		
		//To Read the data from excel file
		FileInputStream efis=new FileInputStream(".\\src\\test\\resources\\TestData (2).xlsx");
		Workbook wb=WorkbookFactory.create(efis);
		String ORGNAME = wb.getSheet("Organization").getRow(1).getCell(2).toString();
		
		//Step1: To launch browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")){
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		

		
		//Step2: Login with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step3: Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step4: Click on Create Organization look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step5: Fill all the mandatory fields
		Random r=new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+random);
		
		//Step6: Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String org = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(org.contains(ORGNAME+random)) {
			System.out.println(org+" added successfully");
		}else {
			System.out.println(org+" failed to add");
		}
		
		//Step7: Logout
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		
		//Step8: close the browser
		driver.quit();
	}

}
