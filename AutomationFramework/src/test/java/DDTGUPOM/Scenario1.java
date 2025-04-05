package DDTGUPOM;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import GenericUtility.ExcelFileUtility;
import GenericUtility.PropertyFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.ContactsInfoPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class Scenario1 {

	public static void main(String[] args) throws IOException {
		
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil=new WebDriverUtility();
		
		//To read the data from property file
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To read the data from excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		
		//Step1: To launch the Browser
		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		wutil.toMaximize(driver);
		wutil.toWaitForElement(driver);
		
		
		//Step2: Login with valid credentials
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		
		
		//Step3: Navigate to contact link
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		//Step4: click on create contact look up image
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactLookUpImg().click();
		
		//Step5: Create contacts with mandatory fields
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getContactSaveButton().click();
		
		//Step6: Verify
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String name = cip.getContactHeader().getText();
		if(name.contains(LASTNAME)) {
			System.out.println(name+" Added Successfully");
		}else {
			System.out.println(name+" Failed to Add");
		}
		
		//Step7: Logout
		WebElement administrator = hp.getAdministratorLink();
		wutil.toMouseHover(driver,administrator);
		hp.getSignoutLink().click();
		
		//Step8:
		driver.quit();
		
	}

}
