package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	public WebDriver driver;
	public ProductsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createProductsLookUpIcon;
	@FindBy(name = "search_text")
	private WebElement searchforTf;
	@FindBy(name = "search_field")
	private WebElement inDD;
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getInDD() {
		return inDD;
	}

	public WebElement getSearchforTf() {
		return searchforTf;
	}

	public WebElement getCreateProductsLookUpIcon() {
		return createProductsLookUpIcon;
	}
	
}
