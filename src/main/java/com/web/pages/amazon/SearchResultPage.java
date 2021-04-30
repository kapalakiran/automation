package com.web.pages.amazon;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class SearchResultPage extends BaseFunctions{

	@FindBy(css="span[class*=a-price-whole]")
	private List<WebElement> itempriceTextList;
	
	@FindBy(css="span[class*='a-size-medium a-color-base a-text-normal']")
	private List<WebElement> itemNameTextList;
	
	public SearchResultPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	/**
	 * @author kirankumar 
	 * @description - To get price of a item
	 * @param itemName
	 * @return
	 */
	public String getItemPrice(String itemName) {
		return matchTextAndFetchTextFromOtherWebElementHavingSameIndex(itemNameTextList,itempriceTextList,itemName);
	}
}
