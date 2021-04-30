package com.web.pages.tripadvisor;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class UserReviewPage extends BaseFunctions{

	@FindBy(css="div[class$=bigRatingParent] span[class*='ui_bubble_rating fl bubble']")
	private WebElement bubbleRatingBtn;

	@FindBy(className = "ui_bubble_rating fl bubble_50")
	private WebElement bubbleFiveRatingBtn;

	@FindBy(id="ratingFlag")
	private WebElement ratingFlagTxt;

	@FindBy(css="div[id^='AMENITY_RATING_BUBBLE_TEXT']")
	private List<WebElement> amenityTxtList;

	@FindBy(css="input#ReviewTitle")
	private WebElement reviewTitleTb;

	@FindBy(css="textarea[id='ReviewText']")
	private WebElement reviewTb;

	@FindBy(css="input#noFraud")
	private WebElement submitYourReviewCb;

	@FindBy(xpath="//span[contains(@class,'bubble') and contains(@class,'qid')]")
	private List<WebElement> hotelRatings;

	@FindBy(xpath="//div[text()='Hotel Ratings']")
	private WebElement hotelRatingsSection;
	
	@FindBy(css="div[class$=bigRatingParent] span[class*='ui_bubble_rating fl bubble']")
	private WebElement overallRating;

	public UserReviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	/**
	 * @author kirankumar
	 * @description - To select Five rating
	 * @return
	 */
	public Boolean selectOverallFiveRating() {
		try {
			moveToElementAndClick(overallRating);
			if(ratingFlagTxt.getText().equals("Excellent"))
				return true;
		}catch (Exception e) {
			logFailed(e.toString());
		}
		return false;
	}
	/**
	 * @author kirankumar
	 * @description to select submit button 
	 * @return
	 */
	public Boolean selectSubmitYourReviewCb() {
		scrollToElement(submitYourReviewCb, "Submit Checkbox");
		return click(submitYourReviewCb, "Submit Checkbox");
	}

	/**
	 * @author kirankumar
	 * @throws InterruptedException 
	 * @description - to add review title & review
	 */
	public void writeReview() throws InterruptedException {
		enterText(reviewTitleTb,getRandomCharacterLimit(5),"Review Title");
		enterText(reviewTb,getRandomCharacterLimit(201),"Review");
	}
	/**
	 * @author kirankumar  
	 * @throws InterruptedException 
	 * @description to select all the Hotel Reviews & verify its selected or not
	 */
	public Boolean selectAllTheHotelReviews() throws InterruptedException {
		scrollToElement(hotelRatingsSection, "Hotel Rating");
		List<Boolean> amenity = new ArrayList<Boolean>();
		for(int i=0;i<3;i++) {
			moveToElementAndClick(hotelRatings.get(i));
			Thread.sleep(1000);
			if(amenityTxtList.get(i).getText().equals("Excellent"))
				amenity.add(true);
			else
				amenity.add(false);

		}
		long count = amenity.stream().filter(p -> p == true).count();
		if(count ==3)
			return true;
		else
			return false;

	}
}
