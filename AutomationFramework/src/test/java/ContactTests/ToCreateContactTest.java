package ContactTests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;
import GenericUtility.ExcelFileUtility;
import ObjectRepository.ContactsInfoPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateContactPage;
import ObjectRepository.HomePage;
@Listeners(GenericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass{
	
	@Test(groups = "smoke")
	public void ToCreateContactTest_TC_001() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactLookUpImg().click();
		ExcelFileUtility eutil=new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getContactSaveButton().click();
		ContactsInfoPage cip=new ContactsInfoPage(driver);
		String name = cip.getContactHeader().getText();
		Assert.assertTrue(name.contains(LASTNAME));
		Reporter.log(name+" Added Successfully", true);
		System.out.println("-------");
	}
}