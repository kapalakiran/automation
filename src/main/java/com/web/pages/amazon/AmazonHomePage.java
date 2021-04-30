package com.web.pages.amazon;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class AmazonHomePage extends BaseFunctions{

	@FindBy(id="twotabsearchtextbox")
	private WebElement globalSearchTb;
	
	
	@FindBy(css="div[id*=issDiv]")
	private List<WebElement> globalSearchDrpDwns;
	
	public AmazonHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @author kirankumar 
	 * @description To search and select the item
	 * @param item
	 * @return
	 * @throws InterruptedException
	 */
	public Boolean searchAndSelectItem(String item) throws InterruptedException {
		waitUntilElementFound(globalSearchTb);
		enterText(globalSearchTb, item, "Item Name");
		Thread.sleep(2000);
		return selectValueFromListOfWebElements(globalSearchDrpDwns, item);
	}
	
	
	
}
