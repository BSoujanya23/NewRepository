package ContactTests;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;
import GenericUtility.ExcelFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.ContactsInfoPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.HomePage;
@Listeners(GenericUtility.ListenersImplementation.class)
public class ToCreateContactWithOrgTest extends BaseClass {

	@Test(groups = "smoke")
	public void toCreateContactWithOrgTest_TC_005() throws EncryptedDocumentException, IOException {

		// Click on contacts
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// Click on create Contact
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactLookUpImg().click();

		// To pass Data in Lastname
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);

		// To click on org look up image
		ccp.getAddOrgLookUpImg().click();
		WebDriverUtility wutil = new WebDriverUtility();

		// To switch driver control to child window
		wutil.toSwitchWindow(driver, "Accounts");

		// Click on Mphasis
		driver.findElement(By.xpath("//a[text()='Mphasis622']")).click();

		// To switch back driver control to parent
		wutil.toSwitchWindow(driver, "Contacts");

		// Save and verify
		ccp.getContactSaveButton().click();
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String name = cip.getContactHeader().getText();
		Assert.assertTrue(name.contains(LASTNAME));
		Reporter.log(name + " Added Successfully", true);
	}
}
