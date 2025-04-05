package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPage {
	
	public OrgInfoPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement orgHeader;

	public WebElement getOrgHeader() {
		return orgHeader;
	}
}
