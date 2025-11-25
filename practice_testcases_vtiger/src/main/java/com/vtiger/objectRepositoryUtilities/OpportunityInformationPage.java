package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityInformationPage {
	public WebDriver driver;
	public OpportunityInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement header;
	@FindBy(id = "dtlview_Opportunity Name")
	private WebElement actOppoName;
	@FindBy(xpath = "//a[@title='Contacts']")
	private WebElement actRelatedContact;
	@FindBy(xpath = "//a[@title='Organizations']")
	private WebElement actRelatedOrg;
	public WebElement getHeader() {
		return header;
	}
	public WebElement getActOppoName() {
		return actOppoName;
	}
	public WebElement getActRelatedContact() {
		return actRelatedContact;
	}
	public WebElement getActRelatedOrg() {
		return actRelatedOrg;
	}
	
	
}
