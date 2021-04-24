package com.web.pages.flipkart;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class FlipkartHomePage extends  BaseFunctions{

	@FindBy(xpath="//span[.='Enter Email/Mobile number']/preceding::input[1]")
	private WebElement email_Mob_Tb;
	
	@FindBy(xpath="//span[.='Enter Password']/preceding::input[1]")
	private WebElement password_Tb;
	
	@FindBy(css="button[type='submit'] span")
	private WebElement loginBtn;
	
	public FlipkartHomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	public boolean login() throws IOException {
		enterText(email_Mob_Tb,getProperty("Email") , "Email");
		enterText(password_Tb,getProperty("Password"), "Password");
		return click(loginBtn, "login");
	}
}
