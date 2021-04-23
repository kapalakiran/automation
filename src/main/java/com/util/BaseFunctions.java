package com.util;

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
}
