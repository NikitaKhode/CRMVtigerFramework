package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericWebDriverUtility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	@FindAll({@FindBy(linkText = "Opportunities"),@FindBy(xpath = "//a[@href='index.php?module=Potentials&action=index']")})
	private WebElement oppoetunityLink;
	@FindBy(linkText = "Products")
	private WebElement productlink;
	@FindBy(linkText = "Documents")
	private WebElement documentLink;
	@FindBy(linkText = "Trouble Tickets")
	private WebElement trobleTicketLink;
	@FindBy(linkText = "Leads")
	private WebElement leadsLink;
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminBtn;
	@FindBy(linkText = "Sign Out")
	private WebElement signoutBtn;
	@FindBy(linkText = "Products")
	private WebElement productsLink;
	@FindBy(linkText = "More")
	private WebElement moreLink;
	@FindBy(linkText = "Vendors")
	private WebElement vendorsLink;
	
	public WebElement getVendorsLink() {
		return vendorsLink;
	}
	public void movetoMorelink(WebDriver driver) {
		Actions act=new Actions(driver);
		act.moveToElement(moreLink);
	}
	public WebElement getMoreLink() {
		return moreLink;
	}
	public WebElement getProductsLink() {
		return productsLink;
	}
	public WebElement getSignoutBtn() {
		return signoutBtn;
	}
	public WebElement getAdminBtn() {
		return adminBtn;
	}
	public WebElement getOrgLink() {
		return orgLink;
	}
	public WebElement getContactLink() {
		return contactLink;
	}
	public WebElement getOppoetunityLink() {
		return oppoetunityLink;
	}
	public WebElement getProductlink() {
		return productlink;
	}
	public WebElement getDocumentLink() {
		return documentLink;
	}
	public WebElement getTrobleTicketLink() {
		return trobleTicketLink;
	}
	public WebElement getLeadsLink() {
		return leadsLink;
	}
	public void signOut() throws InterruptedException {
		WebDriverUtility wutil=new WebDriverUtility();
		Thread.sleep(2000);
		wutil.moveToElementAndClick(driver, adminBtn);
		Thread.sleep(2000);
		wutil.moveToElementAndClick(driver, signoutBtn);
		Thread.sleep(2000);
	}
	
}
