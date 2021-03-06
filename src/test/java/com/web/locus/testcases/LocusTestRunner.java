package com.web.locus.testcases;

import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.util.BaseFunctions;
import com.web.pages.flipkart.FlipkartHomePage;
import com.web.pages.flipkart.SearchedItemPage;

public class LocusTestRunner extends BaseFunctions{

	@Test(enabled=true,groups= {"Regression"})
	@Parameters({"SearchItem","Min","Max","Brand1","Brand2"})
	public void verifyFlipKartSearchAndFilterFunctionalities(String searchItem,String min,String max,String brand1,String brand2) throws IOException, InterruptedException {
		logInfo("To verify search, filter & add to cart functionality");
		FlipkartHomePage objFlipkartHomePage = new FlipkartHomePage(driver);
		objFlipkartHomePage.selectCancelInLoginPopUp();
		objFlipkartHomePage.searchItemAndVerifyIt(searchItem);
		SearchedItemPage objSearchedItemPage = new SearchedItemPage(driver);
		Boolean selectFilterStatus = objSearchedItemPage.selectMinAndMaxAndVerifyItInFilterValues(min,max);
		Boolean selectBrandStatus = objSearchedItemPage.selectBrand(Arrays.asList(brand1,brand2));
		Boolean addToCartStatus = objSearchedItemPage.selectItemAndAddToCart();
		if(selectBrandStatus && selectFilterStatus && addToCartStatus)
			logPassed("Able to verify search,filter & add to cart functionality");
		else
			logFailed("Unable to verify search,filter & add to cart functionality");
	}
}
