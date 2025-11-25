package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	public WebDriver driver;
	public OrganizationInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headermsg;
	@FindBy(id="mouseArea_Industry")
	private WebElement industrytxtfld;
	@FindBy(id="dtlview_Phone")
	private WebElement phonenumbertxtfld;

	public WebElement getHeadermsg() {
		return headermsg;
	}

	public WebElement getIndustrytxtfld() {
		return industrytxtfld;
	}

	public WebElement getPhonenumbertxtfld() {
		return phonenumbertxtfld;
	}
	
}
