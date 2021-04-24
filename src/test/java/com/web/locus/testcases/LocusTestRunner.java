package com.web.locus.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.util.TestBase;
import com.web.pages.flipkart.FlipkartHomePage;

public class LocusTestRunner extends TestBase{

//	@BeforeMethod(alwaysRun=true)
//	public void settingUpTheTest() {
//		System.out.println("T");
//	}
	
	@Test
	public void verify() throws IOException {
		new FlipkartHomePage(driver).login();
	}
}
