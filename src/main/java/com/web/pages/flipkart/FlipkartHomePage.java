package com.web.pages.flipkart;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class FlipkartHomePage extends  BaseFunctions{

	@FindBy(xpath="//span[.='Enter Email/Mobile number']/preceding::input[1]")
	public WebElement email_Mob_Tb;
	
	@FindBy(xpath="//span[.='Enter Password']/preceding::input[1]")
	public WebElement password_Tb;
	
	@FindBy(css="button[type='submit'] span")
	public WebElement login_Btn;
	
	@FindBy(xpath="//button[.='✕']")
	public WebElement cancel_Btn;
	
	@FindBy(css="input[title*='Search']")
	public WebElement search_Tb;
	
	@FindBy(css="div#container form[class*=header-form-search]  span:nth-child(1)")
	private List<WebElement> search_DrpDwns;
	
	public FlipkartHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	public boolean login() throws IOException {
		enterText(email_Mob_Tb,getProperty("Email") , "Email");
		enterText(password_Tb,getProperty("Password"), "Password");
		return click(login_Btn, "login");
	}
	
	/**
	 * @author kirankumar
	 * @description to select close button in login pop-up
	 * @return Boolean
	 */
	public Boolean selectCancelInLoginPopUp() {
		waitUntilElementFound(cancel_Btn);
		return click(cancel_Btn, "Cancel");
	}
	
	/**
	 * @author kirankumar
	 * @param itemToSearch
	 * @return Boolean
	 * @description to search the item, verify search drop-downs & then select relevant data
	 */
	public Boolean searchItemAndSelectIt(String itemToSearch) {
		enterText(search_Tb,itemToSearch,"Search Textbox");
		if(verifySearchTextInListOfWebElements(search_DrpDwns,itemToSearch))
			logPaassed(itemToSearch+" search is working as expected");
		else
			logPaassed(itemToSearch+" search is improper");
	    return selectValueFromListOfWebElements(search_DrpDwns,itemToSearch);
	}
}
