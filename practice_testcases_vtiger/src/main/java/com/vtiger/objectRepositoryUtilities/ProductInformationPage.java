package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {
	public WebDriver driver;
	public ProductInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement header;
	@FindBy(id = "dtlview_Product Name")
	private WebElement actProductName;
	public WebElement getHeader() {
		return header;
	}
	public WebElement getActProductName() {
		return actProductName;
	}
	
}
