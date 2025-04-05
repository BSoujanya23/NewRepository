package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
	public CreateContactPage(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameTextfield;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement addOrgLookUpImg;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement contactSaveButton;

	public WebElement getLastnameTextfield() {
		return lastnameTextfield;
	}

	public WebElement getAddOrgLookUpImg() {
		return addOrgLookUpImg;
	}

	public WebElement getContactSaveButton() {
		return contactSaveButton;
	}
}
