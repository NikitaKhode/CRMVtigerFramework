package com.vtiger.generic.ListenerUtility;

import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.genericWebDriverUtility.UtilityClassObject;

public class ListenerImpClass implements ISuiteListener, ITestListener{
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	public ITestResult result;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		//spark report config
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
				spark=new ExtentSparkReporter("./AdvanceReports/reports_"+time+".html");
				spark.config().setDocumentTitle("CRM test reports");
				spark.config().setReportName("CRM reports");
				spark.config().setTheme(Theme.DARK);
		//add environment info and create test
				report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "WINDOWS_10)");
				report.setSystemInfo("BROWSER", "Chrome");
	}
	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("======"+result.getMethod().getMethodName()+"======START========");
		 test = report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("======"+result.getMethod().getMethodName()+"======SUCCESS========");
		UtilityClassObject.getTest().log(Status.PASS, result.getMethod().getMethodName()+ " is Pass");
	//	UtilityClassObject.getTest().log(Status.PASS, result.getMethod().getMethodName()+" Is Success");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("======"+result.getMethod().getMethodName()+"======FAILURE========");
		UtilityClassObject.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		String testName = result.getMethod().getMethodName();
		
		TakesScreenshot ts=(TakesScreenshot)UtilityClassObject.getDriver();
		String file=ts.getScreenshotAs(OutputType.BASE64);
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		UtilityClassObject.getTest().addScreenCaptureFromBase64String(file+testName+time);
		UtilityClassObject.getTest().log(Status.FAIL, "Screenshot captured");
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("======"+result.getMethod().getMethodName()+"======SKIPPED========");
		UtilityClassObject.getTest().log(Status.SKIP, result.getMethod().getMethodName()+" is Skipped");
	}
	
}
