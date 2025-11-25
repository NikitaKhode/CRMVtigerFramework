package com.vtiger.leadsModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vtiger.basetest.BaseClass;
import com.vtiger.genericFileUtility.ExcelFileUtility;
import com.vtiger.genericWebDriverUtility.JavaUtility;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.CreateNewLeadsPage;
import com.vtiger.objectRepositoryUtilities.HomePage;
import com.vtiger.objectRepositoryUtilities.LeadsInformationPage;
import com.vtiger.objectRepositoryUtilities.LeadsPage;

public class CreateLeadsTest extends BaseClass {
	@Test(groups = "smoke")
	public void createLeadWithMandatoryFieldsTest() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String LEADNAME = exutil.getDataFromExcelUtilityTest("Leads", 1, 0);
		String COMPANY = exutil.getDataFromExcelUtilityTest("Leads", 1, 1);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		// Step 2: Click on Leads Module
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		// Step 3: Click on create leads lookup icon
		LeadsPage ldp = new LeadsPage(driver);
		ldp.getCreateLeadLookUpIcon().click();
		// Enter lastName
		CreateNewLeadsPage clp = new CreateNewLeadsPage(driver);
		clp.getLastNameTf().sendKeys(LEADNAME + randomNum);
		// Enter Company
		clp.getCompany().sendKeys(COMPANY + randomNum);
		// Step 5: Click on Save
		clp.getLeadsSaveBtn().click();
		// Verify header,LastName and Company
		LeadsInformationPage lip = new LeadsInformationPage(driver);
		String header = lip.getHeader().getText();
		if (header.contains(LEADNAME)) {
			System.out.println("Lead created with Mandatory Fields");
		} else {
			System.out.println("Failed to create lead with Mandatory fields");
		}
		String lastName = lip.getLastNameTxtfld().getText();
		if (lastName.equals(LEADNAME + randomNum)) {
			System.out.println("Last Name verified : " + LEADNAME + randomNum);
		} else {
			System.out.println("Failed to verify lastName");
		}
		String company = lip.getCompanytxtfld().getText();
		if (company.equals(COMPANY + randomNum)) {
			System.out.println("Company name verified :" + COMPANY + randomNum);
		} else {
			System.out.println("Failed to verify Company");
		}
		// logout
	}

	@Test(groups = "regression")
	public void createLeadAssignedToGroupsTest() throws EncryptedDocumentException, IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String ORGNAME = exutil.getDataFromExcelUtilityTest("Organization", 1, 0);
		String LEADNAME = exutil.getDataFromExcelUtilityTest("Leads", 1, 0);
		String COMPANY = exutil.getDataFromExcelUtilityTest("Leads", 1, 1);
		String expectedAssignedgrp = exutil.getDataFromExcelUtilityTest("Leads", 1, 2);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
	
		// Step 2: Click on Leads Module
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		// Step 3: Click on create leads lookup icon
		LeadsPage ldp = new LeadsPage(driver);
		ldp.getCreateLeadLookUpIcon().click();
		// Enter lastName
		CreateNewLeadsPage clp = new CreateNewLeadsPage(driver);
		clp.getLastNameTf().sendKeys(LEADNAME + randomNum);
		// Enter Company
		clp.getCompany().sendKeys(COMPANY + randomNum);
		// Select Groups radio button
		clp.getRadioBtn().click();
		// Select any group from dropdown
		WebElement selGrpDrpdwon = clp.getSelGroupDD();
		wutil.select(selGrpDrpdwon, expectedAssignedgrp);
		// Step 5: Click on Save
		clp.getLeadsSaveBtn().click();
		// verify header,LastName,Company, Assigned to which group
		LeadsInformationPage lip = new LeadsInformationPage(driver);
		String header = lip.getHeader().getText();
		if (header.contains(LEADNAME)) {
			System.out.println("Lead created with Mandatory Fields");
		} else {
			System.out.println("Failed to create lead with Mandatory fields");
		}
		String lastName = lip.getLastNameTxtfld().getText();
		if (lastName.equals(LEADNAME + randomNum)) {
			System.out.println("Last Name verified : " + LEADNAME + randomNum);
		} else {
			System.out.println("Failed to verify lastName");
		}
		String company = lip.getCompanytxtfld().getText();
		if (company.equals(COMPANY + randomNum)) {
			System.out.println("Company name verified :" + COMPANY + randomNum);
		} else {
			System.out.println("Failed to verify Company");
		}
		String actAssignedgrp = driver.findElement(By.xpath("//a[text()='" + expectedAssignedgrp + "']")).getText();
		if (actAssignedgrp.equals(expectedAssignedgrp)) {
			System.out.println("Lead created with Group assigned to : " + expectedAssignedgrp);
		} else {
			System.out.println("Failed to verify Assigned To which group");
		}
		// logout
	}

	@Test
	public void createLeadsBySelectingAllDropsdowns() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String LEADNAME = exutil.getDataFromExcelUtilityTest("Leads", 1, 0);
		String COMPANY = exutil.getDataFromExcelUtilityTest("Leads", 1, 1);
		String expectedAssignedgrp = exutil.getDataFromExcelUtilityTest("Leads", 1, 2);
		String leadSource = exutil.getDataFromExcelUtilityTest("Leads", 1, 3);
		String indutry = exutil.getDataFromExcelUtilityTest("Leads", 1, 4);
		String leadStatus = exutil.getDataFromExcelUtilityTest("Leads", 1, 5);
		String rating = exutil.getDataFromExcelUtilityTest("Leads", 1, 6);
		String firstNamedrpdwn = exutil.getDataFromExcelUtilityTest("Leads", 3, 0);
		String firstName = exutil.getDataFromExcelUtilityTest("Leads", 3, 1);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		
		// Step 2: Click on Leads Module
		HomePage hp = new HomePage(driver);
		hp.getLeadsLink().click();
		// Step 3: Click on create leads lookup icon
		LeadsPage ldp = new LeadsPage(driver);
		ldp.getCreateLeadLookUpIcon().click();
		// Select FirstName dropdown and enter FirstName
		CreateNewLeadsPage clp = new CreateNewLeadsPage(driver);
		WebElement selFNdrpdwm = clp.getSelFNDD();
		wutil.select(firstNamedrpdwn, selFNdrpdwm);
		clp.getFirstNameTf().sendKeys(firstName + randomNum);
		// Enter lastName
		clp.getLastNameTf().sendKeys(LEADNAME + randomNum);
		// Enter Company
		clp.getCompany().sendKeys(COMPANY + randomNum);
		// Select Groups radio button
		clp.getRadioBtn().click();
		// Select any group from dropdown
		WebElement selGrpDrpdwon = clp.getSelGroupDD();
		wutil.select(selGrpDrpdwon, expectedAssignedgrp);
		// Select Lead sourse
		WebElement selLeadSource = clp.getSelLeadSource();
		wutil.select(leadSource, selLeadSource);
		// Select Industry
		WebElement selIndustry = clp.getIndustryDD();
		wutil.select(indutry, selIndustry);
		// Select LeadStatus
		WebElement selLeadStatus = clp.getLeadStatusDD();
		wutil.select(leadStatus, selLeadStatus);
		// Select rating
		WebElement selRating = clp.getRatingDD();
		wutil.select(rating, selRating);
		// Step 5: Click on Save
		clp.getLeadsSaveBtn().click();
		// verify header
		LeadsInformationPage lip = new LeadsInformationPage(driver);
		String header = lip.getHeader().getText();
		boolean status=header.contains(LEADNAME);
		Assert.assertEquals(status, true);
		// verify FirstName
		String actFirstName = lip.getFirstNameTxtfld().getText();
		SoftAssert sa=new SoftAssert();
		boolean statusFirstName=actFirstName.contains(firstNamedrpdwn + firstName + randomNum);
		sa.assertEquals(statusFirstName, true);
	
		// verify LastName
		String lastName = lip.getLastNameTxtfld().getText();
		sa.assertEquals(lastName, LEADNAME + randomNum);
	
		// Verify Company
		String company = lip.getCompanytxtfld().getText();
		sa.assertEquals(company, COMPANY + randomNum);
	
		// Verify Assigned to Which grop
		String actAssignedgrp = driver.findElement(By.xpath("//a[text()='" + expectedAssignedgrp + "']")).getText();
		sa.assertEquals(actAssignedgrp, expectedAssignedgrp);
		
		// Verify Lead source
		String actLeadsource = lip.getActualLeadSorce().getText();
		sa.assertEquals(actLeadsource, leadSource);
		
		// Verify Industry
		String actIndustry = lip.getActIndustry().getText();
		sa.assertEquals(actIndustry, indutry);
		
		// Verify Lead Status
		String actLeadStatus = lip.getActLeadStatus().getText();
		sa.assertEquals(actLeadStatus, leadStatus);
		
		// Verify Rating
		String actRating = lip.getActRating().getText();
		sa.assertEquals(actRating, rating);
		// logout

	}

}