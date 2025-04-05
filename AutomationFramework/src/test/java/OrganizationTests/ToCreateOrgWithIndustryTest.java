package OrganizationTests;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericUtility.BaseClass;
import GenericUtility.ExcelFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.CreateOrgPage;
import ObjectRepository.HomePage;
import ObjectRepository.OrgInfoPage;
import ObjectRepository.OrgPage;
@Listeners(GenericUtility.ListenersImplementation.class)
public class ToCreateOrgWithIndustryTest extends BaseClass{
	
	@Test(groups = "Regression")
	public void toCreateOrgWithIndustryTest_TC_003() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		OrgPage op=new OrgPage(driver);
		op.getOrgLookUpImg().click();
		Random r=new Random();
		int random = r.nextInt(1000);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		String INDUSTRYNAME = eutil.toReadDataFromExcelFile("Organization",5,3);
		CreateOrgPage cop=new CreateOrgPage(driver);
		cop.getOrgNameTextfield().sendKeys(ORGNAME+random);
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.toHandleDropdown(cop.getIndustryDropdown(), INDUSTRYNAME);
		cop.getOrgSaveButton().click();
		OrgInfoPage oip=new OrgInfoPage(driver);
		String org = oip.getOrgHeader().getText();
		Assert.assertTrue(org.contains(ORGNAME+random));
		Reporter.log(org+" Added Successfully", true);
	}

}
