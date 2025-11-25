package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchBasicModePage {
	WebDriver driver;
	public SearchBasicModePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "search_text")
	private WebElement searchTf;
	@FindBy(name="search")
	private WebElement searchBtn;
	public WebElement getSearchTf() {
		return searchTf;
	}
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	
}
