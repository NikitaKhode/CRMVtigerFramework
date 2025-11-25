package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewVendorPage {
	public WebDriver driver;
	public CreateNewVendorPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="vendorname")
	private WebElement vendorNameTf;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	public WebElement getVendorNameTf() {
		return vendorNameTf;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
}
