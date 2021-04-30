package com.web.pages.tripadvisor;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class HotelDetailPage extends BaseFunctions{

	@FindBy(css="div[data-test-target=review-title] a")
	private List<WebElement> reviewTitles;
	
	@FindBy(css="div[data-test-target=ugc_cta_button]")
	private WebElement writeAReviewBtn;
	
	public HotelDetailPage(WebDriver driver){
		PageFactory.initElements(driver,this);
	}
	
	/**
	 * @author kirankumar 
	 * @description To select the first review
	 */
	public void selectFirstReview() {
		scrollToElement(reviewTitles.get(0), "Review");
		waitUntilElementFound(reviewTitles.get(0));
		click(reviewTitles.get(0), "1st review");		
	}
	
	/**
	 * @author kirankumar 
	 * @throws InterruptedException 
	 * @description To write a review 
	 */
	public void selectWriteAReview() throws InterruptedException {
		scrollToElement(writeAReviewBtn, "Review");
		waitUntilElementFound(writeAReviewBtn);
		click(writeAReviewBtn, "Write A Review");
		switchToLastTabWithOutURL();
	}
}
