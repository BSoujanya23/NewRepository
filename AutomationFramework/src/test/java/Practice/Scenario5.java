package Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario5 {

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

		// Step3: Naviagte to contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step4: Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step5: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("soujanya");

		// Step6: Select the organization from organization look up page
		// To Capture the parentwindow id
		String parent = driver.getWindowHandle();

		// to click on plus symbol
		driver.findElement(By.xpath("//img[contains(@onclick,'return window.open')]")).click();

		// to capture all the childwindows id
		Set<String> all = driver.getWindowHandles();
		all.remove(parent);

		// to switch the driver control to child window
		for (String child : all) {
			driver.switchTo().window(child);
		}

		// to add the organization
		driver.findElement(By.id("1")).click();
		// to transfer the driver control back to parent window
		driver.switchTo().window(parent);

		// Step7: Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains("soujanya")) {
			System.out.println(lastname + "----Added Successfully");
		} else {
			System.out.println(lastname + "----Failed to add");
		}

		// Step7: Logout of Application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step8: To Close the browser
		driver.quit();

	}

}
