package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewDocumentPage {
	public WebDriver driver;
	public CreateNewDocumentPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "notes_title")
	private WebElement titleTf;
	@FindBy(xpath = "//input[@onclick='toggleAssignType(this.value)' and @value='T']")
	private WebElement radioBtn;
	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement selGropDD;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement docSaveBtn;
	
	public WebElement getRadioBtn() {
		return radioBtn;
	}

	public WebElement getSelGropDD() {
		return selGropDD;
	}

	public WebElement getDocSaveBtn() {
		return docSaveBtn;
	}

	public WebElement getTitleTf() {
		return titleTf;
	}
	
}
