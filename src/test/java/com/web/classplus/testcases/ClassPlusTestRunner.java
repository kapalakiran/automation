package com.web.classplus.testcases;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.util.BaseFunctions;
import com.web.pages.amazon.AmazonHomePage;
import com.web.pages.amazon.SearchResultPage;
import com.web.pages.flipkart.FlipkartHomePage;
import com.web.pages.flipkart.SearchedItemPage;
import com.web.pages.tripadvisor.HotelDetailPage;
import com.web.pages.tripadvisor.TripAdvisorPage;
import com.web.pages.tripadvisor.UserReviewPage;

public class ClassPlusTestRunner extends BaseFunctions{

	@Test(enabled=true,groups= {"Regression"})
	@Parameters({"SearchItem","SearchKeyword"})
	public void comparePriceFromFlipkartAndAmazon(String searchItem,String searchKeyword) throws IOException, InterruptedException {
		logInfo("To fetch the iPhone XR (64GB) - Yellow in Amazon & Flipkart and then compare it" );	
		updateUrl(getProperty("AmazonURL"));
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

	@Test
	@Parameters({"SearchText"})
	public void verifyTripAdvisor(String searchItem) throws IOException, InterruptedException {
		logInfo("To search and select trip write a review, perform overall rating, write review, select all hotel reviews & click on submit you review checkbox");
		updateUrl(getProperty("TripAdvisorURL"));
		TripAdvisorPage objTripAdvisorPage = new TripAdvisorPage(driver);
		Boolean searchStatus = objTripAdvisorPage.searchAndSelectFirstDrpDwn("Club Mahindra");
		HotelDetailPage objHotelDetailPage = new HotelDetailPage(driver);
		objHotelDetailPage.selectWriteAReview();
		UserReviewPage objUserReviewPage = new UserReviewPage(driver);
		Boolean overallRating = objUserReviewPage.selectOverallFiveRating();
		objUserReviewPage.writeReview();
		objUserReviewPage.selectAllTheHotelReviews();
		Boolean submitYourReviewStatus = objUserReviewPage.selectSubmitYourReviewCb();
		List<Boolean> lstOfValidations = Arrays.asList(submitYourReviewStatus,searchStatus,overallRating);
		long count = lstOfValidations.stream().filter(p -> p == true).count();
		if(count == 3) {
			logPassed("To search and select trip write a review, perform overall rating, write review, select all hotel reviews & click on submit you review checkbox");
			System.out.println("To search and select trip write a review, perform overall rating, write review, select all hotel reviews & click on submit you review checkbox");
		}else {
			logFailed("Test Case - to search and select trip write a review, perform overall rating, write review, select all hotel reviews & click on submit you review checkbox failed");
			System.out.println("Test Case - to search and select trip write a review, perform overall rating, write review, select all hotel reviews & click on submit you review checkbox failed");
		}

	}
}
