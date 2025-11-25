package com.vtiger.opportunityModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vtiger.basetest.BaseClass;
import com.vtiger.genericFileUtility.ExcelFileUtility;
import com.vtiger.genericWebDriverUtility.JavaUtility;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.ContactPage;
import com.vtiger.objectRepositoryUtilities.CreateNewContactPage;
import com.vtiger.objectRepositoryUtilities.CreateNewOpportunityPage;
import com.vtiger.objectRepositoryUtilities.CreateNewOrgPage;
import com.vtiger.objectRepositoryUtilities.HomePage;
import com.vtiger.objectRepositoryUtilities.OpportunitiesPage;
import com.vtiger.objectRepositoryUtilities.OpportunityInformationPage;
import com.vtiger.objectRepositoryUtilities.OrganizationsPage;

public class CreateOpportunityTest extends BaseClass{
	@Test(groups = "regression")
	public void createOpportunityAssignedToGroupsTest() throws IOException, InterruptedException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String ORGNAME = exutil.getDataFromExcelUtilityTest("organization", 1, 0);
		String OPPONAME = exutil.getDataFromExcelUtilityTest("Opportunity", 1, 0);
		String GROUP = exutil.getDataFromExcelUtilityTest("Opportunity", 1, 1);
		int randomNum = jutil.createRandomNumber();

		// Step 1: Login

		// PRE-CONDITION
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupIcon().click();
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.getOrgNameTf().sendKeys(ORGNAME + randomNum);
		cnop.getSaveBtn().click();
		Thread.sleep(1000);
		String orgForPreCondition = driver.findElement(By.xpath("//span[text()='"+ORGNAME + randomNum+"']")).getText();
		Thread.sleep(2000);
		// Step 2: click on Oppoertunities Module
		Thread.sleep(2000);
		hp.getOppoetunityLink().click();
		// Step 3: click on Create Opportunity lookup icon
		OpportunitiesPage opp = new OpportunitiesPage(driver);
		opp.getCreateOpportunityLookUpIcon().click();
		// Step 4: Enter Opportunity Name
		CreateNewOpportunityPage cnopp = new CreateNewOpportunityPage(driver);
		cnopp.getOppoName().sendKeys(OPPONAME + randomNum);
		// Step 5: Enter Related to Organization
		cnopp.getSelLookUpIcon().click();
		wutil.switchToTabOnUrl(driver, "module=Accounts&action");
		cnopp.getSearchTxt().sendKeys(orgForPreCondition);
		cnopp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgForPreCondition+"']")).click();
		wutil.switchToTabOnUrl(driver, "module=Potentials&action");
		// Step 5:click on radio button grops
		cnopp.getRadioBtn().click();
		WebElement gropDropDown = cnopp.getGroupDD();
		wutil.select(gropDropDown, "Support Group");
		// Step 6: click on save
		cnopp.getSaveBtn().click();
		// Step 7: Verify a) Header msg
		OpportunityInformationPage opip = new OpportunityInformationPage(driver);
		String header = opip.getHeader().getText();
		boolean status=header.contains(OPPONAME);
		Assert.assertEquals(status, true);
		
		// verify Assigned to groups
		// String assignedTo = driver.findElement(By.id("mouseArea_Assigned
		// To")).getText();
		String assignedTo = driver.findElement(By.xpath("//a[text()='" + GROUP + "']")).getText();
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(assignedTo, GROUP);
		// logout
	}

	@Test
	public void createOpportunityRelatedToOrgTest() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		String ORGNAME = exutil.getDataFromExcelUtilityTest("organization", 1, 0);
		String OPPONAME = exutil.getDataFromExcelUtilityTest("Opportunity", 1, 0);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		
		// PRE-CONDITION: CREATE ORG
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupIcon().click();
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.getOrgNameTf().sendKeys(ORGNAME + randomNum);
		cnop.getSaveBtn().click();
		String orgForPreCondition =driver.findElement(By.xpath("//span[text()='"+ORGNAME + randomNum+"']")).getText();
		// Step 2: click on Oppoertunities Module
		hp.getOppoetunityLink().click();
		// Step 3: click on Create Opportunity lookup icon
		OpportunitiesPage opp = new OpportunitiesPage(driver);
		opp.getCreateOpportunityLookUpIcon().click();
		// Step 4: Enter Opportunity Name
		CreateNewOpportunityPage cnopp = new CreateNewOpportunityPage(driver);
		cnopp.getOppoName().sendKeys(OPPONAME + randomNum);
		// Step 5: Enter Related to Organization
		cnopp.getSelLookUpIcon().click();
		wutil.switchToTabOnUrl(driver, "module=Accounts&action");
		cnopp.getSearchTxt().sendKeys(orgForPreCondition);
		cnopp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgForPreCondition + "']")).click();
		wutil.switchToTabOnUrl(driver, "module=Potentials&action");
		// Step 6: Click on save
		cnopp.getSaveBtn().click();
		// Step 7: Verify a) Header msg b) Opportunity Name c) Related to 'Org Name'
		OpportunityInformationPage opip = new OpportunityInformationPage(driver);
		String header = opip.getHeader().getText();
		boolean status=header.contains(OPPONAME);
		Assert.assertEquals(status, true);
		
		String actOppoName = opip.getActOppoName().getText();
		boolean status2=actOppoName.contains(OPPONAME);
		Assert.assertEquals(status2, true);
	
		// String
		// actRelatedTo=driver.findElement(By.xpath("//a[text()='"+orgForPreCondition+"+']")).getText();
		String actRelatedOrg = opip.getActRelatedOrg().getText();
		Assert.assertEquals(actRelatedOrg, orgForPreCondition);
	
		// logout

	}

	@Test
	public void createOpportunityRelatedToContactTest() throws EncryptedDocumentException, IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		String LASTNAME = exutil.getDataFromExcelUtilityTest("Contact", 1, 0);
		String OPPONAME = exutil.getDataFromExcelUtilityTest("Opportunity", 1, 0);
		int randomNum = jutil.createRandomNumber();

		// Step 1: Login

		// PRE-CONDITION: CREATE CONTACT
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLookupIcon().click();
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastnameTf().sendKeys(LASTNAME + randomNum);
		cncp.getSaveBtn().click();
		String contactForPreCondition = driver.findElement(By.xpath("//span[text()='"+LASTNAME + randomNum+"']")).getText();
		// Step 2: click on Oppoertunities Module
		hp.getOppoetunityLink().click();
		// Step 3: click on Create Opportunity lookup icon
		OpportunitiesPage opp = new OpportunitiesPage(driver);
		opp.getCreateOpportunityLookUpIcon().click();
		// Step 4: Enter Opportunity Name
		CreateNewOpportunityPage cnopp = new CreateNewOpportunityPage(driver);
		cnopp.getOppoName().sendKeys(OPPONAME + randomNum);
		// Step 5: Enter Related to Contact
		WebElement selDropDown = cnopp.getSelDropDown();
		wutil.select("Contacts", selDropDown);
		cnopp.getSelLookUpIcon().click();
		wutil.switchToTabOnUrl(driver, "module=Contacts&action");
		cnopp.getSearchTxt().sendKeys(contactForPreCondition);
		cnopp.getSearchBtn().click();
		// driver.findElement(By.xpath("//a[contains(text(),'"+contactForPreCondition+"')]")).click();
		// driver.findElement(By.xpath("//a[contains(text(),'"+contactForPreCondition+"')
		// and @id='1']")).click();
		driver.findElement(By.id("1")).click();
		wutil.switchToTabOnUrl(driver, "module=Potentials&parenttab=Sal");
		// Step 6: Click on save
		cnopp.getSaveBtn().click();
		// Step 7: Verify a) Header msg b) Opportunity Name c) Related to 'Org Name'
		OpportunityInformationPage opip = new OpportunityInformationPage(driver);
		String header = opip.getHeader().getText();
		boolean status=header.contains(OPPONAME);
		Assert.assertEquals(status, true);
	
		String actOppoName = opip.getActOppoName().getText();
		Assert.assertEquals(actOppoName, OPPONAME + randomNum);
		
		String actRelatedContact = opip.getActRelatedContact().getText();
		SoftAssert sa=new SoftAssert();
		boolean status2=actRelatedContact.contains(contactForPreCondition);
		sa.assertEquals(status2, true);
		
		// logout
	}
}
