package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorLink;
	
	@FindBy(linkText="Sign Out")
	private WebElement signoutLink;

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getAdministratorLink() {
		return administratorLink;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}
}
