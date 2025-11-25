package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	public WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="lastname")
	private WebElement lastnameTf;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	@FindBy(xpath = "//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']")
	private WebElement selectOrgNameLookupIcon;
	@FindBy(name = "support_start_date")
	private WebElement startDateElemnt;
	@FindBy(name = "support_end_date")
	private WebElement endDateElement;
	
	public WebElement getEndDateElement() {
		return endDateElement;
	}
	public WebElement getSelectOrgNameLookupIcon() {
		return selectOrgNameLookupIcon;
	}
	public WebElement getLastnameTf() {
		return lastnameTf;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getStartDateElemnt() {
		return startDateElemnt;
	}
	
}
