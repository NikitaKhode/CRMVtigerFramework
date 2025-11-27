package com.vtiger.contactsModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.basetest.BaseClass;
import com.vtiger.genericFileUtility.ExcelFileUtility;

import com.vtiger.genericWebDriverUtility.JavaUtility;
import com.vtiger.genericWebDriverUtility.UtilityClassObject;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.ContactInformationPage;
import com.vtiger.objectRepositoryUtilities.ContactPage;
import com.vtiger.objectRepositoryUtilities.CreateNewContactPage;
import com.vtiger.objectRepositoryUtilities.CreateNewOrgPage;
import com.vtiger.objectRepositoryUtilities.HomePage;
import com.vtiger.objectRepositoryUtilities.OrganizationsPage;
import com.vtiger.objectRepositoryUtilities.SearchBasicModePage;

public class CreateContactTest extends BaseClass{
	@Test(groups = "smoke")
	public void createContactWithMandatoryFieldsTest() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		
		String LASTNAME = exutil.getDataFromExcelUtilityTest("Contact", 1, 0);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		
		// Step 2:Navigate to Contacts Module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// Step 3:Click on Create Contacts Lookup icon
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLookupIcon().click();
		// Step 4:Enter LastName
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastnameTf().sendKeys(LASTNAME+randomNum);
		// Step 5:CLick on Save
		cncp.getSaveBtn().click();
		// Verify name on the header of the msg for LastName
		ContactInformationPage cip = new ContactInformationPage(driver);
		String header = cip.getContactheadermsg().getText();
		boolean status=header.contains(LASTNAME);
		Assert.assertEquals(status, true);
		String actlastName = driver.findElement(By.id("mouseArea_Last Name")).getText();
		boolean status2=actlastName.contains(LASTNAME);
		Assert.assertEquals(status2, true);
	}

	@Test()
	public void createContactWithOrganizationTest() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String LASTNAME = exutil.getDataFromExcelUtilityTest("Contact", 1, 0);
		String ORGNAME = exutil.getDataFromExcelUtilityTest("Organization", 1, 0);
		int randomNum = jutil.createRandomNumber();
	
		// Step 1: Login
		// PRE-CONDITION: CREATE ORG
		// Navigate to Org module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupIcon().click();
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.getOrgNameTf().sendKeys(ORGNAME + randomNum);
		cnop.getSaveBtn().click();
		String orgforSearch = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		// Step 2:Navigate to Contacts Module
		hp.getContactLink().click();
		// Step 3:Click on Create Contacts Lookup icon
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLookupIcon().click();
		// Step 4:Enter LastName and enter organization name
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastnameTf().sendKeys(LASTNAME + randomNum);
		cncp.getSelectOrgNameLookupIcon().click();
		wutil.switchToTabOnUrl(driver, "module=Accounts&action");
		SearchBasicModePage sbmp = new SearchBasicModePage(driver);
		sbmp.getSearchTf().sendKeys(orgforSearch);
		sbmp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgforSearch+"']")).click();
		// switchback to parent window
		wutil.switchToTabOnUrl(driver, "module=Contacts&action");
		// Step 5:CLick on Save
		cncp.getSaveBtn().click();
		// Verify name on the header of the msg for LastName
		ContactInformationPage cip = new ContactInformationPage(driver);
		String header = cip.getContactheadermsg().getText();
		boolean status=header.contains(LASTNAME);
		Assert.assertEquals(status, true);
		// Verify Org Name
	//	String actOrgName = driver.findElement(By.xpath("//span[text()='"+orgforSearch+"']")).getText();
		String actOrgName =driver.findElement(By.xpath("(//a[text()='"+orgforSearch+"'])[2]")).getText();
		boolean status2=actOrgName.contains(ORGNAME);
		Assert.assertEquals(status2, true);
	}

	@Test()
	public void createContactWithSupportDateTest() throws InterruptedException, IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();

		String LASTNAME = exutil.getDataFromExcelUtilityTest("Contact", 1, 0);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		// Step 2:Navigate to Contacts Module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		// Step 3:Click on Create Contacts Lookup icon
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLookupIcon().click();
		// Step 4:Enter LastName
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastnameTf().sendKeys(LASTNAME + randomNum);
		// Step 5:Enter Support Start date
		WebElement startDateEle = cncp.getStartDateElemnt();
		startDateEle.clear();
		String currentDate = jutil.getCurrentDate();
		startDateEle.sendKeys(currentDate);
		// Step 6:Enter Support end Date
		WebElement supportEndDate = cncp.getEndDateElement();
		supportEndDate.clear();
		String requiredDate = jutil.getRequiredDate(30);
		supportEndDate.sendKeys(requiredDate);
		// Step 7:CLick on Save
		cncp.getSaveBtn().click();
		Thread.sleep(5000);
		// Verify name on the header of the msg for LastName
		ContactInformationPage cip = new ContactInformationPage(driver);
		String header = cip.getContactheadermsg().getText();
		boolean status=header.contains(LASTNAME);
		Assert.assertEquals(status, true);
//		if (header.contains(LASTNAME)) {
//			System.out.println("Contact Created With LastName " + LASTNAME + randomNum);
//		} else {
//			System.out.println("Failed to create Contact");
//		}
		// Verify StartDate and EndDate
		String actstartdate = driver.findElement(By.xpath("//span[contains(text(),'" + currentDate + "')]")).getText();
		Assert.assertEquals(actstartdate, currentDate);
//		if (actstartdate.equals(currentDate)) {
//			System.out.println("Contact Created With current date " + currentDate);
//		} else {
//			System.out.println("Failed to create Contact with current date ");
//		}
		String actenddate = driver.findElement(By.xpath("//span[contains(text(),'" + requiredDate + "')]")).getText();
		Assert.assertEquals(actenddate, requiredDate);
//		if (actenddate.equals(requiredDate)) {
//			System.out.println("Contact created with Required Date " + requiredDate);
//		}
		// logout
	}

}
