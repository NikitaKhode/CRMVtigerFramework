package com.vtiger.generic.ListenerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImpTest implements IRetryAnalyzer {
	int count=0;
	int countlimit=5;
	@Override
	public boolean retry(ITestResult result) {
		if(count<countlimit) {
			count++;
			return true;
		}
		return false;
	}
	
}
