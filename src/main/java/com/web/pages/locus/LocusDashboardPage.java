package com.web.pages.locus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class LocusDashboardPage extends BaseFunctions {

	
	@FindBy(css="button[data-test-id=button-redirect-to-sso]")
	private WebElement loginBtn;
	
	
	public LocusDashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @author kirankumar 
	 * @description to select the login button
	 * @return
	 */
	public Boolean selectLoginButton() {
		waitUntilElementFound(loginBtn);
		return click(loginBtn, "Login");
	}
}
