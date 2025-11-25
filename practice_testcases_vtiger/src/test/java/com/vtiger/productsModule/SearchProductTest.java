package com.vtiger.productsModule;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.basetest.BaseClass;
import com.vtiger.genericFileUtility.ExcelFileUtility;
import com.vtiger.genericWebDriverUtility.JavaUtility;
import com.vtiger.genericWebDriverUtility.WebDriverUtility;
import com.vtiger.objectRepositoryUtilities.CreateNewProductsPage;
import com.vtiger.objectRepositoryUtilities.HomePage;

import com.vtiger.objectRepositoryUtilities.ProductInformationPage;
import com.vtiger.objectRepositoryUtilities.ProductsPage;

public class SearchProductTest extends BaseClass{
	@Test(groups = {"regression","regional"})
	public void searchProductWithProductNameTest() throws IOException, InterruptedException {
		WebDriverUtility wutil=new WebDriverUtility();
		ExcelFileUtility exutil=new ExcelFileUtility();
		JavaUtility jutil=new JavaUtility();
		String PRODUCTName=exutil.getDataFromExcelUtilityTest("Products", 1, 0);
		String ProductNameDD=exutil.getDataFromExcelUtilityTest("Products", 1, 1);
		int RandomNum=jutil.createRandomNumber();
		HomePage hp=new HomePage(driver);
		hp.getProductsLink().click();
		ProductsPage pp=new ProductsPage(driver);
		pp.getCreateProductsLookUpIcon().click();
		CreateNewProductsPage cnpp=new CreateNewProductsPage(driver);
		cnpp.getPrductNameTf().sendKeys(PRODUCTName+RandomNum);
		cnpp.getSaveBtn().click();
		ProductInformationPage pip=new ProductInformationPage(driver);
		String productForPrecondition = pip.getActProductName().getText();
		hp.getProductlink().click();
		WebElement indropdown = pp.getInDD();
		wutil.select(indropdown, ProductNameDD);
		pp.getSearchforTf().sendKeys(productForPrecondition);
		pp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+productForPrecondition+"' and @title='Products']")).click();
		Thread.sleep(2000);
		String actProductName=pip.getActProductName().getText();
		Assert.assertEquals(actProductName, productForPrecondition);
			System.out.println("Product Search Successfull");
	}
}
