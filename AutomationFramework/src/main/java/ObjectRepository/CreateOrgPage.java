package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPage {
	
	public CreateOrgPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameTextfield;
	
	@FindBy(name="industry")
	private WebElement industryDropdown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropdown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement orgSaveButton;

	public WebElement getOrgNameTextfield() {
		return orgNameTextfield;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getOrgSaveButton() {
		return orgSaveButton;
	}
}
