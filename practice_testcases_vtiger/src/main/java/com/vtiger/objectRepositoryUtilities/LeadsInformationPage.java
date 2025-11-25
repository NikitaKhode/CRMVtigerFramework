package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsInformationPage {
	public WebDriver driver;
	public LeadsInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement header;
	@FindBy(id="dtlview_Last Name")
	private WebElement lastNameTxtfld;
	@FindBy(id="dtlview_Company")
	private WebElement companytxtfld;
	@FindBy(xpath = "//span[@id='dtlview_First Name']/..")
	private WebElement firstNameTxtfld;
	@FindBy(id = "dtlview_Lead Source")
	private WebElement actualLeadSorce;
	@FindBy(id = "dtlview_Industry")
	private WebElement actIndustry;
	@FindBy(id="dtlview_Lead Status")
	private WebElement actLeadStatus;
	@FindBy(id = "dtlview_Rating")
	private WebElement actRating;
	
	public WebElement getActualLeadSorce() {
		return actualLeadSorce;
	}

	public WebElement getActIndustry() {
		return actIndustry;
	}

	public WebElement getActLeadStatus() {
		return actLeadStatus;
	}

	public WebElement getActRating() {
		return actRating;
	}

	public WebElement getFirstNameTxtfld() {
		return firstNameTxtfld;
	}

	public WebElement getLastNameTxtfld() {
		return lastNameTxtfld;
	}

	public WebElement getCompanytxtfld() {
		return companytxtfld;
	}

	public WebElement getHeader() {
		return header;
	}
	
}
