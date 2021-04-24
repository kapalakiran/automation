package com.util;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseFunctions extends TestBase{

	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
	ExtentTest test =null;
	public void waitUntilElementFound(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * @author - kirankumar 
	 * @param - WebElement element, text to enter & Label
	 * @description - To enter text on webelement
	 */

	public void enterText(WebElement element, String textToEnter,String Label) {
		try {
			element.sendKeys(textToEnter);
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
		}
	}
	/**
	 * @author - kirankumar 
	 * @param - WebElement element, Label
	 * @description - To click on webelement
	 */
	public Boolean click(WebElement element, String Label) {
		try {
			element.click();
			logPaassed("Able to click on "+Label);
			return true;
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
			return false;
		}
	}


	public void logPaassed(String passedLog) {
		test.pass(passedLog);
	}

	public void logInfo(String info) {
		test.info(info);
	}

	public void logFailed(String failedLog) {
		test.fail(failedLog);
	}
	@AfterSuite(alwaysRun=true)
	public void closeTheBrowser(){
		driver.close();
		extent.flush();
	}
	public void setExtentReport(ITestContext ctx) {
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Reports");
		extent.attachReporter(spark);
		ExtentTest test = extent.createTest(ctx.getName());
	}

	public Boolean verifySearchTextInListOfWebElements(List<WebElement> lstOfWebElements,String expectedText) {
		List<Boolean> status = new ArrayList<Boolean>();
		for(WebElement webElement : lstOfWebElements) {
			if(webElement.getText().contains(expectedText)) 
				status.add(true);
			else
				status.add(false);
		}
		return status.stream().allMatch(val -> val == true);
	}

	public Boolean selectValueFromListOfWebElements(List<WebElement> lstOfWebElements,String expectedText) {
		Boolean Status = false;
		for(WebElement webElement : lstOfWebElements) {
			if(webElement.getText().equalsIgnoreCase(expectedText)) {
				Status= click(webElement, "Expected Text");
				break;
			}
		}
		return Status;
	}
	
	public boolean scrollToElement(WebElement ele, String desc) {
		Boolean success = false;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			if (desc != null)
				logInfo("Scrolled to :" + desc);
			success = true;
		} catch (Exception e) {
			logFailed("Unable to scroll"+desc+" "+e.getMessage());
		}
		return success;
	}
}
