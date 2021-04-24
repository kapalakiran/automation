package com.web.pages.flipkart;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.util.BaseFunctions;

public class SearchedItemPage extends BaseFunctions{

	@FindBy(xpath="//div/div[.='Brand']/ancestor::section/descendant::input[@type='checkbox']/following-sibling::div[2]")
	private List<WebElement> brand_Cbs;

	@FindBy(xpath="//span[.='Price']/following::Select")
	private List<WebElement> minAndMaxPrice_DrpDwnBtns;

	@FindBy(xpath="//div[text()='✕']/following-sibling::div")
	private List<WebElement> selectedFilter_Text;

	@FindBy(xpath="//div/div[.='Brand']")
	private WebElement brandBtn;

	public SearchedItemPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author kirankumar
	 * @createdDate - 24th April 2021
	 * @param Brands
	 * @return Boolean 
	 * @description - To select brand in the searched item page
	 */
	public Boolean selectBrand(List<String> Brands) {
		Boolean Status = false;
		try {
		scrollToElement(brandBtn, "Brand Filter");
		click(brandBtn, "Brand");
		List<Boolean> brandStatus = new ArrayList<Boolean>();
		for( String expectedBran : Brands)
			brandStatus.add(selectValueFromListOfWebElements(brand_Cbs, expectedBran));
		if(brandStatus.stream().allMatch(val -> val == true))
			Status = true;
		else
			Status = false;

		if(Status) {
			List<Boolean> filterStatus = new ArrayList<Boolean>();
			for(int i=0;i<Brands.size();i++) 
				filterStatus.add(verifySearchTextInListOfWebElements(brand_Cbs,Brands.get(i)));
			if(filterStatus.stream().allMatch(val -> val == true))
				Status = true;
			else
				Status = false;
		}			
		}catch (Exception e) {
			logFailed("Unable to select Brand");
		}
		return Status;
	}
	
	/**
	 * @author kirankumar
	 * @description  To select Min & Max value and verify it in the selected filter values
	 * @param Min
	 * @param Max
	 * @return Boolean
	 */
	public Boolean selectMinAndMaxAndVerifyItInFilterValues(String Min,String Max) {
		Select selectMin = new Select(minAndMaxPrice_DrpDwnBtns.get(0));
		selectMin.selectByVisibleText(Min);
		Select selectMax = new Select(minAndMaxPrice_DrpDwnBtns.get(1));
		selectMax.selectByVisibleText(Max);
		String MinMax = Min+"-"+Max;
		if(verifySearchTextInListOfWebElements(brand_Cbs,MinMax)){
			logPaassed("Able to select the required Min & Max Price");
			return true;
		}else {
			logFailed("Unable to select the required Min & Max Price");
			return false;
		}
	}
}
