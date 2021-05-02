package com.web.pages.locus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class LocusUniversalLoginPage extends BaseFunctions{

	@FindBy(css="input[data-test-id='input-userId']")
	private WebElement userIdTb;

	@FindBy(css="button[data-test-id=button-personnel-continue-to-login]")
	private WebElement continueBtn;

	@FindBy(css="input[data-test-id='input-password']")
	private WebElement passwordTb;

	@FindBy(css="button[data-test-id='button-login-with-password']")
	private WebElement loginWithPwdBtn;

	@FindBy(css="div[data-test-id=error-auth0] p")
	private WebElement erroMsgTxt;

	public LocusUniversalLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	/**
	 * @author kirankumar
	 * @description - to enter username & pwd
	 * @param userName
	 * @param pwd
	 * @return
	 * @throws InterruptedException
	 */
	public Boolean peformLogin(String userName,String pwd) throws InterruptedException {
		enterUserIDAndClickOnContinue(userName);
		return enterPasswordAndClickOnLoginBtn(pwd);
	}
	/**
	 * @author kirankumar 
	 * @description - to enter user id & then click on continue button
	 * @param UserName
	 * @return
	 */
	public Boolean enterUserIDAndClickOnContinue(String UserName) {
		try {
			waitUntilElementFound(userIdTb);
			enterText(userIdTb, UserName, "User ID");
			customWait(2000);
			return click(continueBtn, "Continue");
		}catch (Exception e) {
			logFailed(e.getMessage().toString());
		}
		return false;
	}
	/**
	 * @author kirankumar 
	 * @description - to enter password
	 * @param 
	 * @throws InterruptedException 
	 */
	public Boolean enterPasswordAndClickOnLoginBtn(String pwd) throws InterruptedException {
		waitUntilElementFound(passwordTb);
		enterText(passwordTb,pwd,"Password");
		Boolean Status = click(loginWithPwdBtn,"Login Button with Password");
		Thread.sleep(5000);
		return Status;
	}

	/**
	 * @author kirankumar
	 * @description - to validate ErrorMsg is displayed or not and verify its Msg
	 * @return
	 * @throws InterruptedException 
	 */
	public Boolean validateErrorMsgIsDisplayedOrNotAndVerifyItsMsg() {
		Boolean status = false;
		if(isElementPresent(erroMsgTxt)) {
			if("Wrong ID or password".equals(erroMsgTxt.getText())) {
				logPassed("Error message is validated");
				status =  true;
			}else 
				logFailed("Error message is not populated");
		}
		return status;
	}
}
