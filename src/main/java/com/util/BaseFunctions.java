package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseFunctions extends TestBase{

	public void waitUntilElementFound(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void sendTextWithActions(WebElement element,String textToEnter,String Label) {
		try {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(textToEnter);
		actions.build().perform();
		logPassed("Able to enter text using actions - "+Label);
		}catch (Exception e) {
			logPassed("Unable to enter text using actions - "+Label);
			logFailed(e.getMessage().toString());
		}
		
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
			sendTexWithoutClear(element,textToEnter,Label);
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
		}
	}

	/**
	 * @author kirankumar
	 * @description - send text without clearing the existing text
	 * @param element
	 * @param textToEnter
	 * @param Label
	 */
	public void sendTexWithoutClear(WebElement element, String textToEnter,String Label) {
		try{
			element.sendKeys(textToEnter);
			logPassed("Able to enter text for "+Label);
		}catch (Exception e) {
			logPassed("Unable to enter text for "+Label);
			logFailed(e.getMessage().toString());
		}
	}
	/**
	 * @author - kirankumar
	 * @param webElement
	 * @return
	 */
	public String getTextUsingJS(WebElement webElement) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		return (String) (js.executeScript("return arguments[0].text;", webElement));
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

	public String switchToLastTabWithOutURL() throws InterruptedException {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		int LastTab = tabs.size();
		driver.switchTo().window(tabs.get(LastTab - 1));
		Thread.sleep(2000);
		return driver.getCurrentUrl();
	}

	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public Boolean clickUsingActions(WebElement element,String Label) {
		try {
			Actions actionBuilder = new Actions(driver);
			actionBuilder.moveToElement(element).click(element).build().perform();
			return true;
		}catch (Exception e) {
			logFailed(e.toString());
		}
		return false;
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

	public void updateUrl(String url) {
		driver.get(url);
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

	public boolean containsWithIgnoringCase(String sourceString,String wantedString) {
		return Pattern.compile(Pattern.quote(wantedString), Pattern.CASE_INSENSITIVE).matcher(sourceString).find();
	}

	public String removeSpecialChars(String text) {
		return text.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
	}
	/**
	 * @author kirankumar
	 * @description - To compare text from webelement fetch the text from other webelement with the same index
	 * @param matchWebElement
	 * @param fetchTextWebElement
	 * @param itemName
	 * @return
	 */
	public String matchTextAndFetchTextFromOtherWebElementHavingSameIndex(List<WebElement> matchWebElement,List<WebElement> fetchTextWebElement,String itemName) {
		try {
			for(int i=0;i<matchWebElement.size();i++) {
				if(removeSpecialChars(matchWebElement.get(i).getText()).contains(removeSpecialChars(itemName))) {
					System.out.println(fetchTextWebElement.get(i).getText());
					return fetchTextWebElement.get(i).getText();
				}
			}
		}catch (Exception e) {
			logFailed(e.toString());
		}
		return null;
	}

	public static String nvl(String ifThisIsNull, String replaceThis) {
		return ifThisIsNull == null ? replaceThis : ifThisIsNull;
	}

	public static String chars = "ABCDEFGHIJKLMNOPQRST123456";
	public static String getRandomCharacterLimit(int max) {
		StringBuilder newStrBuilder = new StringBuilder();
		Random rnd = new Random();
		while ( newStrBuilder.length() < max) { // length of the random string.
			int index = (int) (rnd.nextFloat() * chars.length());
			newStrBuilder.append(chars.charAt(index));
		}
		String newStr =  newStrBuilder.toString();
		return newStr;

	}
	
	public void moveToElementAndClick(WebElement webelement) {
		Actions objActions = new Actions(driver);
		objActions.moveToElement(new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(webelement)), 50, 0).click().build().perform();
	}
	
	public void switchToIframe(WebElement element) {
		try {
		driver.switchTo().frame(element);
		logPassed("Able to switch the frame");
		}catch (Exception e) {
			logFailed(e.toString());
			logFailed("Unable to switch the frame");
		}
		
	}
}
