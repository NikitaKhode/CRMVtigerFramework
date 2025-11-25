package com.vtiger.objectRepositoryUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="user_name")
	WebElement usernameTf;
	@FindBy(name="user_password")
	WebElement passwordTf;
	@FindBy(id="submitButton")
	WebElement loginBtn;
	public WebElement getUsernameTf() {
		return usernameTf;
	}
	public WebElement getPasswordTf() {
		return passwordTf;
	}
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	public void login(String un,String pwd) {
		usernameTf.sendKeys(un);
		passwordTf.sendKeys(pwd);
		loginBtn.click();
	}
}
