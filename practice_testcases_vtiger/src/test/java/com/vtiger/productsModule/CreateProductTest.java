package com.vtiger.productsModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.basetest.BaseClass;
import com.vtiger.genericFileUtility.ExcelFileUtility;
import com.vtiger.genericWebDriverUtility.JavaUtility;
import com.vtiger.genericWebDriverUtility.UtilityClassObject;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.CreateNewProductsPage;
import com.vtiger.objectRepositoryUtilities.CreateNewVendorPage;
import com.vtiger.objectRepositoryUtilities.HomePage;
import com.vtiger.objectRepositoryUtilities.ProductInformationPage;
import com.vtiger.objectRepositoryUtilities.ProductsPage;
import com.vtiger.objectRepositoryUtilities.VendorInformationPage;
import com.vtiger.objectRepositoryUtilities.VendorsPage;

public class CreateProductTest extends BaseClass {
	@Test(groups = "smoke")
	public void createProductWithProductNameTest() throws IOException, InterruptedException {
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String PRODUCTName = exutil.getDataFromExcelUtilityTest("Products", 1, 0);
		int RandomNum = jutil.createRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		ProductsPage pp = new ProductsPage(driver);
		Thread.sleep(1000);
		pp.getCreateProductsLookUpIcon().click();
		CreateNewProductsPage cnpp = new CreateNewProductsPage(driver);
		cnpp.getPrductNameTf().sendKeys(PRODUCTName + RandomNum);
		cnpp.getSaveBtn().click();
		ProductInformationPage pip = new ProductInformationPage(driver);
		String header = pip.getHeader().getText();
		if (header.contains(PRODUCTName + RandomNum)) {
			System.out.println("Header contains : " + PRODUCTName + RandomNum);
		} else {
			System.out.println("Header Not Verified");
		}
		String actProductName = UtilityClassObject.getDriver().findElement(By.xpath("//span[text()='" + PRODUCTName + RandomNum + "']"))
				.getText();
		if (actProductName.contains(PRODUCTName + RandomNum)) {
			System.out.println("Product : " + PRODUCTName + RandomNum + " is Verified");
		} else {
			System.out.println("Product Not Verified");
		}
	}

	@Test
	public void createProductWithVendorNameTest() throws InterruptedException, IOException {
		WebDriverUtility wutil = new WebDriverUtility();
		ExcelFileUtility exutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String PRODUCTName = exutil.getDataFromExcelUtilityTest("Products", 1, 0);
		String VENDORName = exutil.getDataFromExcelUtilityTest("Vendors", 1, 0);
		int RandomNum = jutil.createRandomNumber();
		HomePage hp = new HomePage(driver);
		// PRE-CONDITION: create Vendor
		hp.getMoreLink().click();
		Thread.sleep(2000);
		hp.getVendorsLink().click();
		Thread.sleep(2000);
		VendorsPage vp = new VendorsPage(driver);
		vp.getCreateVendorLookUpIcon().click();
		CreateNewVendorPage cnvp = new CreateNewVendorPage(driver);
		cnvp.getVendorNameTf().sendKeys(VENDORName + RandomNum);
		cnvp.getSaveBtn().click();
		VendorInformationPage vip = new VendorInformationPage(driver);
		String vendorForPreCondition = vip.getVendorNameTxtFld().getText();
		// continue product create With vendor
		hp.getProductsLink().click();
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateProductsLookUpIcon().click();
		CreateNewProductsPage cnpp = new CreateNewProductsPage(driver);
		cnpp.getPrductNameTf().sendKeys(PRODUCTName + RandomNum);
		cnpp.getSelVendorNameLookUpIcon().click();
		wutil.switchToTabOnUrl(driver, "module=Vendors&action");
		cnpp.getSearchTxt().sendKeys(vendorForPreCondition);
		cnpp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + vendorForPreCondition + "']")).click();
		wutil.switchToTabOnUrl(driver, "module=Products&action");
		cnpp.getSaveBtn().click();
		Thread.sleep(2000);
		// verify header
		ProductInformationPage pip = new ProductInformationPage(driver);
		String header = pip.getHeader().getText();
		boolean status1=header.contains(PRODUCTName + RandomNum);
		Assert.assertEquals(status1, true);
		
		// verify product Name
		String actProductName = pip.getActProductName().getText();
		actProductName.contains(PRODUCTName + RandomNum);
			System.out.println("ProductName : " + PRODUCTName + RandomNum + " is Verified");
	
		// verify Vendor Name
		String actVendorName = driver.findElement(By.xpath("(//a[text()='" + vendorForPreCondition + "'])[2]"))
				.getText();
		Assert.assertEquals(actVendorName, vendorForPreCondition);
			System.out.println(vendorForPreCondition + ": Vendor Verified");
		
	}
}
