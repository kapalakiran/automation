package com.web.locus.testcases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.util.BaseFunctions;
import com.web.pages.locus.LiveViewPage;
import com.web.pages.locus.LocusDashboardPage;
import com.web.pages.locus.LocusUniversalLoginPage;

import automation.TestData;

public class LocusInterviewAssignment extends BaseFunctions {
	
	@BeforeMethod(alwaysRun=true)
	public void selectTheLoginBtn(){
		LocusDashboardPage objLocusDashboardPage = new LocusDashboardPage(driver);
		objLocusDashboardPage.selectLoginButton();
	}
	@Test(enabled=true)
	@Parameters({"InvalidUserID","InvalidPassword"})
	public void verifyLoginFunctionalityWithInvalidCredentials(String userName,String pwd) throws InterruptedException {
		logInfo("To verify Login functionality with invalid credentials");
		LocusUniversalLoginPage objLocusUniversalLoginPage = new LocusUniversalLoginPage(driver);
		objLocusUniversalLoginPage.peformLogin(userName, pwd);
		if(objLocusUniversalLoginPage.validateErrorMsgIsDisplayedOrNotAndVerifyItsMsg())
			logPassed("Error Message is displayed when we entered with valid credentials");
		else
			logFailed("Error Message is not getting displayed when we entered with valid credentials");
	}

	@Test(enabled=true)
	@Parameters({"ValidUserID","ValidPassword"})
	public void verifyLoginFunctionalityWithValidCredentials(String userName,String pwd) throws IOException, InterruptedException {
		logInfo("To verify Login functionality with valid credentials");
		new LocusUniversalLoginPage(driver).peformLogin(userName, pwd);
		new LiveViewPage(driver).waitForProfileIcon();
		if(getCurrentUrlAndCompareIt(getProperty("ExpectedURL")))
			logPassed("URL should be redirected with valid credentials");
		else
			logFailed("URL is not redirected with valid credentials");
	}

	@Test(enabled=true)
	@Parameters({"ValidUserID","ValidPassword","UserName"})
	public void verifyProfileIconFunctionality(String userId,String pwd,String userName) throws InterruptedException {
		logInfo("To verify profile icon & user menu drop downs");
		new LocusUniversalLoginPage(driver).peformLogin(userId, pwd);
		if(new LiveViewPage(driver).hoverOnProfileAndVerifyUserMenuDropDowns(userName))
			logPassed("Verified profile icon & user menu drop downs");
		else
			logFailed("Unable to verify profile icon & user menu drop downs");
	}

	@Test(enabled=true)
	@Parameters({"ValidUserID","ValidPassword","SearchText"})
	public void verifySearchForAtaskWithTaskId(String userId,String pwd,String searchText) throws InterruptedException {
		logInfo("To verify search for a task with Task Id");
		new LocusUniversalLoginPage(driver).peformLogin(userId, pwd);
		if(new LiveViewPage(driver).searchAndVerifyItsDrpDwn(searchText))
			logPassed("Able to verify search for a task with Task Id");
		else
			logFailed("Unable to verify search for a task with Task Id");
	}
	
	@Test(enabled=true,priority=1)
	@Parameters({"ValidUserID","ValidPassword","Address","VisitType","SLA"})
	public void createAServiceTask(String userId,String pwd,String address,String visitType,String SLA) throws InterruptedException {
		logInfo("Create a service task & verify it");
		new LocusUniversalLoginPage(driver).peformLogin(userId, pwd);
		TestData.setTaskId(new LiveViewPage(driver).addTask(address,visitType,SLA));
		if(TestData.getTaskId().length()!=0)
			logPassed("Able to Create a service task & verify it");
		else
			logFailed("Unable to Create a service task & verify it");
	}
	
	@Test(priority=2)
	@Parameters({"ValidUserID","ValidPassword"})
	public void searchForCreatedTask(String userId,String pwd) throws InterruptedException {
		logInfo("To verify search for a created task with Task Id");
		new LocusUniversalLoginPage(driver).peformLogin(userId, pwd);
		if(new LiveViewPage(driver).searchAndVerifyItsDrpDwn(TestData.getTaskId()))
			logPassed("Able to verify search for a created task with Task Id");
		else
			logFailed("Unable to verify search for a created task with Task Id");
		
	}
}