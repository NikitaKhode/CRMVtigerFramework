package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductsPage {
	public WebDriver driver;
	public CreateNewProductsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "productname")
	private WebElement prductNameTf;
	@FindBy(name = "search_text")
	private WebElement searchTxt;
	@FindBy(name="search")
	private WebElement searchBtn;
	
	public WebElement getSearchTxt() {
		return searchTxt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSelVendorNameLookUpIcon() {
		return selVendorNameLookUpIcon;
	}
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement selVendorNameLookUpIcon;
	
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getPrductNameTf() {
		return prductNameTf;
	}
	
}
