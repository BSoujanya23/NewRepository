package Practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario2 {

	public static void main(String[] args) {
		// Step1: Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step2: Login to the application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step3: Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step4: Click on create organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step5: Create organization with mandatory fields
		Random r=new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("Mphasis"+random);

		// Step6: Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String organizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationName.contains("Mphasis"+random)) {
			System.out.println(organizationName + " Added Successfully");
		} else {
			System.out.println(organizationName + " Failed to Add");
		}

		// Step7: Logout Of Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step8: Close the browser
		driver.quit();
	}

}
