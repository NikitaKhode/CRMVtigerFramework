package com.vtiger.organizationModule;


import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.vtiger.basetest.BaseClass;
import com.vtiger.genericFileUtility.ExcelFileUtility;
import com.vtiger.genericWebDriverUtility.JavaUtility;
import com.vtiger.genericWebDriverUtility.UtilityClassObject;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.CreateNewOrgPage;
import com.vtiger.objectRepositoryUtilities.HomePage;
import com.vtiger.objectRepositoryUtilities.OrganizationInformationPage;
import com.vtiger.objectRepositoryUtilities.OrganizationsPage;

@Listeners(com.vtiger.generic.ListenerUtility.ListenerImpClass.class)
public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "smoke")
	public void createOrgWithMandatoryFieldsTest() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String ORGNAME = exutil.getDataFromExcelUtilityTest("Organization", 1, 0);
		int randomNum = jutil.createRandomNumber();
		
		// Step 1: Login
		UtilityClassObject.getTest().log(Status.INFO, "Login to application");
		// Step 2: Navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org link");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// Step 3: Click on Create Organization button
		UtilityClassObject.getTest().log(Status.INFO, "Navigated to Create Org link");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupIcon().click();
		// Step 4: Enter all the details and Create new Org
		UtilityClassObject.getTest().log(Status.INFO, "Entered all deatils");
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.getOrgNameTf().sendKeys(ORGNAME + randomNum);
		// click on save and create organization
		UtilityClassObject.getTest().log(Status.INFO, "Click on save");
		cnop.getSaveBtn().click();
		// Verify name on the header of the msg
		UtilityClassObject.getTest().log(Status.INFO, "Verify header");
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String header = oip.getHeadermsg().getText();
		boolean status = header.contains(ORGNAME);
		Assert.assertEquals(status, true);
		// logout
	}

	@Test(groups = "regression")
	public void createOrgWithIndustryTest() throws EncryptedDocumentException, IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String ORGNAME = exutil.getDataFromExcelUtilityTest("Organization", 1, 0);
		String INDUSTRY = exutil.getDataFromExcelUtilityTest("Organization", 1, 1);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		
		// Step 2: Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// Step 3: Click on Create Organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupIcon().click();
		// Step 4: Enter Org name and Industry
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.getOrgNameTf().sendKeys(ORGNAME + randomNum);
		cnop.selectIndustryDD();
		// click on save and create organization
		cnop.getSaveBtn().click();
		// Verify name on the header of the msg
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String header = oip.getHeadermsg().getText();
		boolean status = header.contains(ORGNAME);
		Assert.assertEquals(status, true);
		// Verify Industry
		String industryText = oip.getIndustrytxtfld().getText();
		Assert.assertEquals(industryText, INDUSTRY);
		// logout
	}

	@Test
	public void createOrgWithPhoneNumberTest() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String ORGNAME = exutil.getDataFromExcelUtilityTest("Organization", 1, 0);
		String PHONENUM = exutil.getDataFromExcelUtilityTest("Organization", 1, 2);
		int randomNum = jutil.createRandomNumber();

		// Step 1: Login
		// Step 2: Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// Step 3: Click on Create Organization button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupIcon().click();
		// Step 4: Enter Org name and Phone number
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.getOrgNameTf().sendKeys(ORGNAME + randomNum);
		cnop.getPhoneNumberTf().sendKeys(PHONENUM);
		// click on save and create organization
		cnop.getSaveBtn().click();
		// Verify name on the header of the msg
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String header = oip.getHeadermsg().getText();
		boolean status=header.contains(ORGNAME);
		Assert.assertEquals(status, true);
		// Verify PhoneNumber
		String actuPhoneNum = oip.getPhonenumbertxtfld().getText();
		Assert.assertEquals(actuPhoneNum, PHONENUM);
		// logout
	}
}
