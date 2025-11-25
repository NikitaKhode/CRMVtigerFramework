package com.vtiger.genericWebDriverUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void waitForElementToPresent(WebDriver driver,WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void maximizethePage(WebDriver driver) {
		driver.manage().window().maximize();
	}
	public void switchToTabOnUrl(WebDriver driver,String partialUrl) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windowId = it.next();
			driver.switchTo().window(windowId);
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains(partialUrl)) {
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver,String partialTitle) {
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		while(it.hasNext()) {
			String windowId = it.next();
			driver.switchTo().window(windowId);
			String actualTitle=driver.getTitle();
			if(actualTitle.contains(partialTitle)) {
				break;
			}
		}
	}
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String name_id) {
		driver.switchTo().frame(name_id);
	}
	public void switchToFrame(WebDriver driver,WebElement ele) {
		driver.switchTo().frame(ele);
	}
	public void select(WebElement ele,String text) {
		Select sel=new Select(ele); 
		sel.selectByVisibleText(text);
	}
	public void select(WebElement ele,int index) {
		Select sel=new Select(ele);
		sel.selectByIndex(index);
	}
	public void select(String value,WebElement ele) {
		Select sel=new Select(ele);
		sel.selectByValue(value);
	}
	public void mouseHover(WebDriver driver,WebElement ele) {
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
	}
	public void doubleClick(WebDriver driver,WebElement ele) {
		Actions act=new Actions(driver);
		act.doubleClick(ele).perform();
	}
	public void moveToElementAndClick(WebDriver driver,WebElement ele) throws InterruptedException {
		Actions act=new Actions(driver);
		Thread.sleep(3000);
		act.moveToElement(ele).click().perform();
	}
}




















