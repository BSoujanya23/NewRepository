package GenericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class BaseClass {

	PropertyFileUtility putil = new PropertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver;//To use in Listners

	@BeforeSuite(groups = { "smoke", "Regression" })
	public void beforeSuiteConfiguration() {
		Reporter.log("--DataBase Connection established--", true);
	}

	//@Parameters("browser") // Cross Browser Testing
	//@BeforeTest  //Cross Browser Testing
	 @BeforeClass(groups = { "smoke", "Regression" })
	public void beforeClassConfiguration(/* String BROWSER*/ ) throws IOException {
		 String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver=driver;//Listeners
		Reporter.log("Browser Launched Successfully", true);
		wutil.toMaximize(driver);
		wutil.toWaitForElement(driver);
		driver.get(URL);
	}

	@BeforeMethod(groups = { "smoke", "Regression" })
	public void beforeMethodConfiguration() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("Logged in Successfully", true);
	}

	@AfterMethod(groups = { "smoke", "Regression" })
	public void afterMethodConfiguration() {
		HomePage hp = new HomePage(driver);
		wutil.toMouseHover(driver, hp.getAdministratorLink());
		hp.getSignoutLink().click();
		Reporter.log("Logged Out Successfully", true);
	}

	@AfterClass(groups = { "smoke", "Regression" })
	public void afterClassConfiguration() {
		Reporter.log("Successfully Closed the Browser", true);
		driver.quit();
	}

	@AfterSuite(groups = { "smoke", "Regression" })
	public void afterSuiteConfiguration() {
		Reporter.log("--DataBase Disconnected Successfully--", true);
	}
}
