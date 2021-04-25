package com.web.pages.flipkart;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class DetailedItemViewPage extends BaseFunctions{

	@FindBy(xpath="//span[.='Size- UK/India']/following::ul[1]/li[starts-with(@id,'sw')]")
	private List<WebElement> sizeTiles;

	@FindBy(xpath="//ul/li//button[not(@disbaled)]")
	private List<WebElement> addToCartAndBuyNowBtns;

	@FindBy(xpath="//span[.='Login or Signup']")
	private WebElement login_SignUpText;

	@FindBy(xpath="//span[.='Deliver to']")
	private WebElement deliverToText;
	
	public DetailedItemViewPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	/**
	 * @author kirankumar 
	 * @description - To select a size of the shoe which has inv. & then verify Login Page is appearing or not
	 * @return
	 */
	public Boolean selectSizeAndAddToCart() {
		int i =0;
		scrollDown();
		do {
			clickUsingActions(sizeTiles.get(i), "size");
	         i++;
		}while(!isElementPresent(addToCartAndBuyNowBtns.get(0))); 
		clickUsingActions(addToCartAndBuyNowBtns.get(1), "Buy Now");
		if(isElementPresent(login_SignUpText))
			return true;
		else
			return false;
	}
}
