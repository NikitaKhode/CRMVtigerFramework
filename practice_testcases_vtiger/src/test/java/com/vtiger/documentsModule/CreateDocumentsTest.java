package com.vtiger.documentsModule;

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
import com.vtiger.objectRepositoryUtilities.CreateNewDocumentPage;
import com.vtiger.objectRepositoryUtilities.DocumentInformationPage;
import com.vtiger.objectRepositoryUtilities.DocumentPage;
import com.vtiger.objectRepositoryUtilities.HomePage;

public class CreateDocumentsTest extends BaseClass{
	@Test(groups = "smoke")
	public void createDocumentWithTitle() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String DOCNAME = exutil.getDataFromExcelUtilityTest("Documents", 1, 0);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		
		// Step 2: Click on Documents Module
		HomePage hp = new HomePage(driver);
		hp.getDocumentLink().click();
		// Step 3: Click on create documents lookup icon
		DocumentPage dp = new DocumentPage(driver);
		dp.getCreateDocumentLookupIcon().click();
		// Step 4: Enter Title
		CreateNewDocumentPage cdp = new CreateNewDocumentPage(driver);
		cdp.getTitleTf().sendKeys(DOCNAME + randomNum);
		// Step 5: Click on Save
		cdp.getDocSaveBtn().click();
		// Step 6: Verify Title
		DocumentInformationPage dip = new DocumentInformationPage(driver);
		String titleField = dip.getTitleTxtFld().getText();
		Assert.assertEquals(titleField, DOCNAME + randomNum);
		// logout
	}
	
	@Test(groups = "regression")
	public void CreateDocumentsAssignedToGroup() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String ORGNAME = exutil.getDataFromExcelUtilityTest("Organization", 1, 0);
		String DOCNAME = exutil.getDataFromExcelUtilityTest("Documents", 1, 0);
		String expectedAssignedgrp = exutil.getDataFromExcelUtilityTest("Documents", 1, 1);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
	
		// Step 2: Click on Documents Module
		HomePage hp = new HomePage(driver);
		hp.getDocumentLink().click();
		// Step 3: Click on create documents lookup icon
		DocumentPage dp = new DocumentPage(driver);
		dp.getCreateDocumentLookupIcon().click();
		// Step 4: Enter Title
		CreateNewDocumentPage cdp = new CreateNewDocumentPage(driver);
		cdp.getTitleTf().sendKeys(DOCNAME + randomNum);
		// Click on Groups radio button
		cdp.getRadioBtn().click();
		// Select any group from dropdown
		WebElement selGrpDrpdwon = cdp.getSelGropDD();
		wutil.select(selGrpDrpdwon, expectedAssignedgrp);
		// Step 5: Click on Save
		cdp.getDocSaveBtn().click();
		// Step 6: Verify Title
		DocumentInformationPage dip = new DocumentInformationPage(driver);
		String titleField = dip.getTitleTxtFld().getText();
		Assert.assertEquals(titleField, DOCNAME + randomNum);

		// Verify Assigned to Which Group
		String actAssignedgrp = driver.findElement(By.xpath("//a[text()='"+expectedAssignedgrp+"']")).getText();
		Assert.assertEquals(actAssignedgrp, expectedAssignedgrp);
		// logout
	}

}
