package com.web.locus.testcases;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.util.TestBase;
import com.web.pages.flipkart.FlipkartHomePage;
import com.web.pages.flipkart.SearchedItemPage;

public class LocusTestRunner extends TestBase{

//	@BeforeMethod(alwaysRun=true)
//	public void settingUpTheTest() {
//		System.out.println("T");
//	}
	
	@Test
	public void verifyFlipKartSearchAndFilterFunctionalities() throws IOException {
		FlipkartHomePage objFlipkartHomePage = new FlipkartHomePage(driver);
		objFlipkartHomePage.selectCancelInLoginPopUp();
		objFlipkartHomePage.searchItemAndSelectIt("Shoe");
		SearchedItemPage objSearchedItemPage = new SearchedItemPage(driver);
		objSearchedItemPage.selectMinAndMaxAndVerifyItInFilterValues("₹500","₹4000");
		objSearchedItemPage.selectBrand(Arrays.asList("Addidas","Reebok"));
	}
}
