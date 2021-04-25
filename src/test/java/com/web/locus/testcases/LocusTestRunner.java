package com.web.locus.testcases;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.util.TestBase;
import com.web.pages.flipkart.FlipkartHomePage;
import com.web.pages.flipkart.SearchedItemPage;

public class LocusTestRunner extends TestBase{
	
	@Test
	public void verifyFlipKartSearchAndFilterFunctionalities() throws IOException, InterruptedException {
		FlipkartHomePage objFlipkartHomePage = new FlipkartHomePage(driver);
		objFlipkartHomePage.selectCancelInLoginPopUp();
		objFlipkartHomePage.searchItemAndSelectIt("Shoes");
		SearchedItemPage objSearchedItemPage = new SearchedItemPage(driver);
		objSearchedItemPage.selectMinAndMaxAndVerifyItInFilterValues("₹500","₹4000");
		objSearchedItemPage.selectBrand(Arrays.asList("Adidas","Reebok"));
	}
}
