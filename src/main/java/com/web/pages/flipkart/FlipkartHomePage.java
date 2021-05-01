package com.web.pages.flipkart;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class FlipkartHomePage extends  BaseFunctions{

	@FindBy(xpath="//span[.='Enter Email/Mobile number']/preceding::input[1]")
	public WebElement emailOrMobileTb;

	@FindBy(xpath="//span[.='Enter Password']/preceding::input[1]")
	public WebElement passwordTb;

	@FindBy(css="button[type='submit'] span")
	public WebElement loginBtn;

	@FindBy(xpath="//button[.='✕']")
	public WebElement cancelBtn;

	@FindBy(css="input[title*='Search']")
	public WebElement search_Tb;

	@FindAll({@FindBy(css="ul li span"),@FindBy(css="ul li div:nth-child(2)")})
	private List<WebElement> searchDrpDwns;

	public FlipkartHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author kirankumar
	 * @description to select close button in login pop-up
	 * @return Boolean
	 */
	public Boolean selectCancelInLoginPopUp() {
		waitUntilElementFound(cancelBtn);
		return click(cancelBtn, "Cancel");
	}

	/**
	 * @author kirankumar
	 * @param itemToSearch
	 * @return Boolean
	 * @description to search the item, verify search drop-downs & then select relevant data
	 */
	public Boolean searchItemAndVerifyIt(String itemToSearch) {
		sendTextAndEnter(search_Tb,itemToSearch,"Search Textbox");
		return new SearchedItemPage(driver).verifySearchText(itemToSearch);
	}
}