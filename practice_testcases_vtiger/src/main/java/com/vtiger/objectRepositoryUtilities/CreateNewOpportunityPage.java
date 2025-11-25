package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOpportunityPage {
	public WebDriver driver;
	public CreateNewOpportunityPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "potentialname")
	private WebElement OppoName;
	@FindBy(id = "related_to_type")
	private WebElement selDropDown;
	@FindBy(name = "search_text")
	private WebElement searchTxt;
	@FindBy(name="search")
	private WebElement searchBtn;
	@FindBy(xpath = "(//input[@name='assigntype'])[2]")
	private WebElement radioBtn;
	@FindBy(name = "assigned_group_id")
	private WebElement groupDD;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	@FindBy(xpath = "//input[@id='related_to_display']/following-sibling::img")
	private WebElement selLookUpIcon;
	public WebElement getOppoName() {
		return OppoName;
	}
	public WebElement getSelDropDown() {
		return selDropDown;
	}
	public WebElement getSearchTxt() {
		return searchTxt;
	}
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	public WebElement getRadioBtn() {
		return radioBtn;
	}
	public WebElement getGroupDD() {
		return groupDD;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getSelLookUpIcon() {
		return selLookUpIcon;
	}
}
