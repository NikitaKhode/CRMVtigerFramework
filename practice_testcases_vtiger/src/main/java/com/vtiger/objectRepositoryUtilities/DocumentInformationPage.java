package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentInformationPage {
	public WebDriver driver;
	public DocumentInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "dtlview_Title")
	private WebElement titleTxtFld;

	public WebElement getTitleTxtFld() {
		return titleTxtFld;
	}
	
}
