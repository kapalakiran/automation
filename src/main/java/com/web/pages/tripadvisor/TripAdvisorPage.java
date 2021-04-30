package com.web.pages.tripadvisor;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;

public class TripAdvisorPage extends BaseFunctions{

	@FindBy(css="div[data-test-attribute=typeahead-SINGLE_SEARCH_HERO] input[type=search]")
	private WebElement searchTb;
	
	@FindBy(css="div#typeahead_results a")
	private List<WebElement> searchDrpDwns;
	
	public TripAdvisorPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	/**
	 * @author kirankumar
	 * @description - To search & select first drop-down
	 * @param searchText
	 */
	public Boolean searchAndSelectFirstDrpDwn(String searchText) {
		try {
			enterText(searchTb,searchText,"Search Text box");
			Thread.sleep(2000);
			return click(searchDrpDwns.get(0), "1st index");
		}catch (Exception e) {
			logFailed(e.toString());
		}
		return false;
	}
	
	
}
