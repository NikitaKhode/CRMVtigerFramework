package com.vtiger.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.vtiger.genericFileUtility.PropertiesFileUtility;
import com.vtiger.genericWebDriverUtility.UtilityClassObject;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.HomePage;
import com.vtiger.objectRepositoryUtilities.LoginPage;

public class BaseClass {
	public  WebDriver driver;
	public PropertiesFileUtility putil=new PropertiesFileUtility();
	public WebDriverUtility wutil=new WebDriverUtility();

	@BeforeSuite(alwaysRun = true)
	public void configBs() {
		System.out.println("Connecting to DB");
		
	}
	@AfterSuite(alwaysRun = true)
	public void configAS() {
		System.out.println("Closed DB connectivity");
	}
	@BeforeTest(alwaysRun = true)
	public void configBT() {
		System.out.println("Executing pre-conditions");
	}
	@AfterTest(alwaysRun = true)
	public void configAT() {
		System.out.println("Executing Post=conditions");
	}
	//@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true)
	public void lauchBrowser() throws IOException {
		System.out.println("Launching Browser");
	//	String BROWSER=browser;
		String BROWSER=putil.getDataFromPropertiesFile("Browser");
	//	driver=new ChromeDriver();
		if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}
	//	sdriver=driver;
		UtilityClassObject.setDriver(driver);
		driver=UtilityClassObject.getDriver();
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		System.out.println("Closed Browser");
		UtilityClassObject.getDriver().close();
	}
	@BeforeMethod(alwaysRun = true)
	public void login() throws IOException{
		String URL=putil.getDataFromPropertiesFile("Url");
		String USERNAME=putil.getDataFromPropertiesFile("Username");
		String PASSWORD=putil.getDataFromPropertiesFile("Password");
		System.out.println("Logging in to Application");
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.login(USERNAME, PASSWORD);
		wutil.maximizethePage(driver);
		wutil.waitForPageToLoad(driver);
	}
	@AfterMethod(alwaysRun = true)
	public void logout() throws InterruptedException {
		System.out.println("Logging out of application");
		HomePage hp=new HomePage(driver);
		Thread.sleep(1000);
		hp.signOut();
	}
	
}
