package com.vtiger.trobleTicketsModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.basetest.BaseClass;
import com.vtiger.genericFileUtility.ExcelFileUtility;
import com.vtiger.genericWebDriverUtility.JavaUtility;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.ContactPage;
import com.vtiger.objectRepositoryUtilities.CreateNewContactPage;
import com.vtiger.objectRepositoryUtilities.CreateNewOrgPage;
import com.vtiger.objectRepositoryUtilities.CreateNewTroubleTicketPage;
import com.vtiger.objectRepositoryUtilities.HomePage;
import com.vtiger.objectRepositoryUtilities.OrganizationsPage;
import com.vtiger.objectRepositoryUtilities.TroubleTicketPage;

public class CreateTicketsTest extends BaseClass{
	@Test(groups = "smoke")
	public void createTicketsWithTitle() throws IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String TITLE = exutil.getDataFromExcelUtilityTest("TroubleTickets", 1, 0);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login

		// Step 2: Click on Trouble Tickets Module
		HomePage hp=new HomePage(driver);
		hp.getTrobleTicketLink().click();
		// Step 3: click on create Trouble ticket lookup icon
		TroubleTicketPage ttp = new TroubleTicketPage(driver);
		ttp.getCreateTTLookupIcon().click();
		// Step 4: Enter Title
		CreateNewTroubleTicketPage cnttp = new CreateNewTroubleTicketPage(driver);
		cnttp.getTitleTf().sendKeys(TITLE + randomNum);
		// Step 5: Click on Save
		cnttp.getSaveBtn().click();
		// Verify Title
		String actTitle = driver.findElement(By.xpath("//span[text()='" + TITLE + randomNum + "']")).getText();
		Assert.assertEquals(actTitle, TITLE + randomNum);
			System.out.println("Trouble Ticket created with Tite :" + TITLE + randomNum);

		// logout
	}

	@Test(groups = "regional")
	public void createTicketWithOrgTest() throws EncryptedDocumentException, IOException, InterruptedException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String ORGNAME = exutil.getDataFromExcelUtilityTest("Organization", 1, 0);
		String TITLE = exutil.getDataFromExcelUtilityTest("TroubleTickets", 1, 0);
		int randomNum = jutil.createRandomNumber();
		String preOrg = "";
		// Step 1: Login

		// PRE-CONDITION:
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLookupIcon().click();
		CreateNewOrgPage cnop = new CreateNewOrgPage(driver);
		cnop.getOrgNameTf().sendKeys(ORGNAME + randomNum);
		cnop.getSaveBtn().click();
		String orgForPreCondition = driver.findElement(By.xpath("//span[text()='"+ORGNAME + randomNum+"']")).getText();
		// Step 2: Click on Trouble Tickets Module
		Thread.sleep(1000);
		hp.getTrobleTicketLink().click();
		// Step 3: click on create Trouble ticket lookup icon
		TroubleTicketPage ttp = new TroubleTicketPage(driver);
		ttp.getCreateTTLookupIcon().click();
		// Step 4: Enter Title
		CreateNewTroubleTicketPage cnttp = new CreateNewTroubleTicketPage(driver);
		cnttp.getTitleTf().sendKeys(TITLE + randomNum);
		// Click on Groups radio button
		cnttp.getGroupRadioBtn().click();
		// Select organization from dropdown
		WebElement selOrg = cnttp.getSelOrg();
		wutil.select("Accounts", selOrg);
		// Select Organization LookUp icon and enter orgname
		cnttp.getSelLookUpIcon().click();
		wutil.switchToTabOnUrl(driver, "module=Accounts&action");
		cnttp.getSearchTxt().sendKeys(orgForPreCondition);
		cnttp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgForPreCondition + "']")).click();
		wutil.switchToTabOnUrl(driver, "module=HelpDesk");
		// driver.findElement(By.name("parent_name")).getText();
		// Step 5: Click on Save
		cnttp.getSaveBtn().click();
		// Verify Title
		String actTitle = driver.findElement(By.xpath("//span[text()='" + TITLE + randomNum + "']")).getText();
		if (actTitle.equals(TITLE + randomNum)) {
			System.out.println("Trouble Ticket created with Tite :" + TITLE + randomNum);
		} else {
			System.out.println("Failed to create Ticket");
		}
		// Verify Organization
		String actOrg = driver.findElement(By.xpath("(//a[text()='" + orgForPreCondition + "'])[2]")).getText();
		Assert.assertEquals(actOrg, orgForPreCondition);
			System.out.println("Trouble ticket created with Org Name : " + orgForPreCondition);
		// logout
	}

	@Test(enabled = false)
	public void createTicketWithContactTest() throws InterruptedException, EncryptedDocumentException, IOException {
		// Generic Utilities
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String TITLE = exutil.getDataFromExcelUtilityTest("TroubleTickets", 1, 0);
		String LASTNAME = exutil.getDataFromExcelUtilityTest("Contact", 1, 0);
		int randomNum = jutil.createRandomNumber();
		// Step 1: Login
		// PRE-CONDITION:
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLookupIcon().click();
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastnameTf().sendKeys(LASTNAME + randomNum);
		cncp.getSaveBtn().click();
		String preContact = driver.findElement(By.xpath("//span[text()='" + LASTNAME + randomNum + "']")).getText();
		// Step 2: Click on Trouble Tickets Module
		hp.getTrobleTicketLink().click();
		// Step 3: click on create Trouble ticket lookup icon
		TroubleTicketPage ttp = new TroubleTicketPage(driver);
		ttp.getCreateTTLookupIcon().click();
		// Step 4: Enter Title
		CreateNewTroubleTicketPage cnttp = new CreateNewTroubleTicketPage(driver);
		cnttp.getTitleTf().sendKeys(TITLE + randomNum);
		// Select Organization LookUp icon and enter orgname
		cnttp.getSelLookUpIcon().click();
		wutil.switchToTabOnUrl(driver, "module=Accounts&action");
		cnttp.getSearchTxt().sendKeys(preContact);
		cnttp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[contains(text(),'" + preContact + "')]")).click();
		wutil.switchToTabOnUrl(driver, "module=HelpDesk");
		// Step 5: Click on Save
		cnttp.getSaveBtn().click();
		// Verify Title
		String actTitle = driver.findElement(By.xpath("//span[text()='" + TITLE + randomNum + "']")).getText();
		if (actTitle.equals(TITLE + randomNum)) {
			System.out.println("Trouble Ticket created with Tite :" + TITLE + randomNum);
		} else {
			System.out.println("Failed to create Ticket");
		}
		Thread.sleep(2000);
		// Verify Organization
		String actContact = driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=DetailView&record=3984']")).getText();
		Assert.assertEquals(actContact, preContact);
			System.out.println("Trouble ticket created with Org Name : " + preContact);
		// logout
	}
}
