package com.web.classplus.testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.util.BaseFunctions;
import com.web.pages.amazon.AmazonHomePage;
import com.web.pages.amazon.SearchResultPage;
import com.web.pages.flipkart.FlipkartHomePage;
import com.web.pages.flipkart.SearchedItemPage;

public class ClassPlusTestRunner extends BaseFunctions{

	@Test(enabled=true,groups= {"Regression"})
	@Parameters({"SearchItem","SearchKeyword"})
	public void comparePriceFromFlipkartAndAmazon(String searchItem,String searchKeyword) throws IOException, InterruptedException {
		logInfo("To fetch the iPhone XR (64GB) - Yellow in Amazon & Flipkart and then compare it" );	
		new AmazonHomePage(driver).searchAndSelectItem(searchItem);
		String amazonPrice = new SearchResultPage(driver).getItemPrice(searchKeyword);
		updateUrl(getProperty("FlipkartURL"));
		FlipkartHomePage objFlipkartHomePage = new FlipkartHomePage(driver);
		objFlipkartHomePage.selectCancelInLoginPopUp();
		objFlipkartHomePage.searchItemAndVerifyIt(searchItem);
		SearchedItemPage objSearchedItemPage = new SearchedItemPage(driver);
		String flipkartPrice = objSearchedItemPage.getItemPrice(searchKeyword);
		amazonPrice = nvl(amazonPrice, "");
		flipkartPrice = nvl(flipkartPrice, "");
		if(!(amazonPrice.length()==0 || flipkartPrice.length()==0)) {
			if(Integer.valueOf(amazonPrice.substring(1))==Integer.valueOf(flipkartPrice.substring(1))) {
				logPassed("Able to search & compare price b/w amazon & flipkart applications");
				System.out.println("Able to search & compare price b/w amazon & flipkart applications");
			}else {
				logFailed("Unable to search & compare price b/w amazon & flipkart applications");
				System.out.println("Unable to search & compare price b/w amazon & flipkart applications");
			}
		}else {
			if(amazonPrice.length()==0) {
				logPassed("Able to search & compare price b/w amazon & flipkart applications, but item is not availble in amazon");
				System.out.println("Able to search & compare price b/w amazon & flipkart applications, but item is not availble in amazon but price in Flipkart is - "+flipkartPrice);
			}else {
				logPassed("Able to search & compare price b/w amazon & flipkart applications, but item is not availble in flipkart");
				System.out.println("Able to search & compare price b/w amazon & flipkart applications, but item is not availble in flipkart but price in Amazon is - "+amazonPrice);
			}
		}
	}
}
