package com.util;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseFunctions extends TestBase{

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
			if(element.getAttribute("value").length()>0|| element.getText().length()>0){
				element.clear();
			}
			element.sendKeys(textToEnter);
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
		}
	}
	/**
	 * @author - kirankumar 
	 * @param element
	 * @param textToEnter
	 * @param Label
	 * @description - To send text on webelement & press enter
	 */
	public void sendTextAndEnter(WebElement element, String textToEnter,String Label) {
		try {
			element.sendKeys(textToEnter+Keys.ENTER);
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
			logPassed("Able to click on "+Label);
			return true;
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
			return false;
		}
	}
	
	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}


	public void logPassed(String passedLog) {
		try {
			test.pass(passedLog);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void logInfo(String info) {
		test.info(info);
	}

	public void logFailed(String failedLog) {
		test.fail(failedLog);
	}

	public Boolean verifySearchTextInListOfWebElements(List<WebElement> lstOfWebElements,String expectedText) {
		List<Boolean> status = new ArrayList<Boolean>();
		for(WebElement webElement : lstOfWebElements) {
			if(webElement.getText().contains(expectedText)) 
				status.add(true);
		}
		if(status.size()==0)
			status.add(false);
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

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
	}
	/**
	 * @author kirankumar 
	 * @description Check whether webelement is present or not
	 * @param element
	 * @return
	 */
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
}
