package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewTroubleTicketPage {
	public WebDriver driver;
	public CreateNewTroubleTicketPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "ticket_title")
	private WebElement titleTf;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement selLookUpIcon;
	@FindBy(name = "search_text")
	private WebElement searchTxt;
	@FindBy(name="search")
	private WebElement searchBtn;
	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupRadioBtn;
	@FindBy(name = "parent_type")
	private WebElement selOrg;
	public WebElement getTitleTf() {
		return titleTf;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getSelLookUpIcon() {
		return selLookUpIcon;
	}
	public WebElement getSearchTxt() {
		return searchTxt;
	}
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	public WebElement getGroupRadioBtn() {
		return groupRadioBtn;
	}
	public WebElement getSelOrg() {
		return selOrg;
	}
	
}
