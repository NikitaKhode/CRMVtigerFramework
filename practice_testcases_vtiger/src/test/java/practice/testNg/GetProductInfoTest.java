package practice.testNg;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vtiger.genericFileUtility.ExcelFileUtility;

public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		//Search product
		driver.findElement(By.xpath("//input[@name='field-keywords']")).sendKeys(brandName,Keys.ENTER);
		//Capture Product info
		String x="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
	}
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		ExcelFileUtility exutil=new ExcelFileUtility();
		int rowCount = exutil.getRowCount("AmazonProduct");
		Object[][] objarr=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
			objarr[i][0]=exutil.getDataFromExcelUtilityTest("AmazonProduct", i+1, 0);
			objarr[i][1]=exutil.getDataFromExcelUtilityTest("AmazonProduct", i+1, 1);
		}
		return objarr;
	}
}
