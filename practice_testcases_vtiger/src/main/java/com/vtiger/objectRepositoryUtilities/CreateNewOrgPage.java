package com.vtiger.objectRepositoryUtilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.vtiger.genericFileUtility.ExcelFileUtility;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;

public class CreateNewOrgPage {
	public WebDriver driver;
	public CreateNewOrgPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="accountname")
	private WebElement OrgNameTf;
	@FindBy(name="website")
	private WebElement websiteTf;
	@FindBy(name = "employees")
	private WebElement employeesTf;
	@FindBy(name = "email2")
	private WebElement otherEmailTf;
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement indutryDD;
	@FindBy(name="phone")
	private WebElement phoneNumberTf;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getOrgNameTf() {
		return OrgNameTf;
	}
	public WebElement getWebsiteTf() {
		return websiteTf;
	}
	public WebElement getEmployeesTf() {
		return employeesTf;
	}
	public WebElement getOtherEmailTf() {
		return otherEmailTf;
	}
	public WebElement getIndutryDD() {
		return indutryDD;
	}
	public WebElement getPhoneNumberTf() {
		return phoneNumberTf;
	}
	public void selectIndustryDD() throws EncryptedDocumentException, IOException {
		Select sel=new Select(indutryDD);
		ExcelFileUtility exutil=new ExcelFileUtility();
		String INDUSTRY=exutil.getDataFromExcelUtilityTest("Organization", 1, 1);
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.select(indutryDD,INDUSTRY );
	}
	
}
