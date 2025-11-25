package com.vtiger.genericWebDriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int createRandomNumber() {
		Random ran=new Random();
		int ranNum = ran.nextInt(5000);
		return ranNum;
	}
	public String getCurrentDate() {
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sim.format(date);
		return currentDate;
	}
	public String getRequiredDate(int days) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sim.format(cal.getTime());
	//	System.out.println(reqDate);
		return reqDate;
	}
}
