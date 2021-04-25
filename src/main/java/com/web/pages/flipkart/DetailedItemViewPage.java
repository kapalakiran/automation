package com.web.pages.flipkart;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.util.BaseFunctions;

public class DetailedItemViewPage extends BaseFunctions{

	@FindBy(css="li[id*='swatch']")
	private List<WebElement> sizeTiles;

	@FindBy(xpath="//ul/li//button[not(@disbaled)]")
	private List<WebElement> addToCart_And_BuyNow_Btns;

	@FindBy(xpath="//span[.='Login or Signup']")
	private WebElement login_SignUpText;

	/**
	 * @author kirankumar 
	 * @description - To select a size of the shoe which has inv. & then verify Login Page is appearing or not
	 * @return
	 */
	public Boolean selectSizeAndAddToCart() {
		int i =0;
		do {
			click(sizeTiles.get(i), sizeTiles.get(i).getText());
		}while(!isElementPresent(addToCart_And_BuyNow_Btns.get(0))); 
		click(addToCart_And_BuyNow_Btns.get(1), "Buy Now");
		if(isElementPresent(login_SignUpText))
			return true;
		else
			return false;
	}
}
