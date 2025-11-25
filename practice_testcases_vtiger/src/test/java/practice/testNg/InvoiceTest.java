package practice.testNg;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vtiger.basetest.BaseClass;

public class InvoiceTest extends BaseClass{
	
	@Test(retryAnalyzer = com.vtiger.generic.ListenerUtility.RetryListenerImpTest.class)
	public void createInvoiceTest() {
		System.out.println("executing createInvoiceTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "abc");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	@Test
	public void createContact() {
		System.out.println("execute createContact");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
