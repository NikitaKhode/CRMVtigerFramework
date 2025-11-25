package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadsPage {
	public WebDriver driver;
	public CreateNewLeadsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="lastname")
	private WebElement lastNameTf;
	@FindBy(name = "company")
	private WebElement company;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement leadsSaveBtn;
	@FindBy(xpath = "//input[@onclick='toggleAssignType(this.value)' and @value='T']")
	private WebElement radioBtn;
	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement selGroupDD;
	@FindBy(name = "salutationtype")
	private WebElement selFNDD;
	@FindBy(name="firstname")
	private WebElement firstNameTf;
	@FindBy(name="leadsource")
	private WebElement selLeadSource;
	@FindBy(name="industry")
	private WebElement industryDD;
	@FindBy(name="leadstatus")
	private WebElement leadStatusDD;
	@FindBy(name="rating")
	private WebElement ratingDD;
	public WebElement getLastNameTf() {
		return lastNameTf;
	}
	public WebElement getCompany() {
		return company;
	}
	public WebElement getLeadsSaveBtn() {
		return leadsSaveBtn;
	}
	public WebElement getRadioBtn() {
		return radioBtn;
	}
	public WebElement getSelGroupDD() {
		return selGroupDD;
	}
	public WebElement getSelFNDD() {
		return selFNDD;
	}
	public WebElement getFirstNameTf() {
		return firstNameTf;
	}
	public WebElement getSelLeadSource() {
		return selLeadSource;
	}
	public WebElement getIndustryDD() {
		return industryDD;
	}
	public WebElement getLeadStatusDD() {
		return leadStatusDD;
	}
	public WebElement getRatingDD() {
		return ratingDD;
	}
	
	
}
