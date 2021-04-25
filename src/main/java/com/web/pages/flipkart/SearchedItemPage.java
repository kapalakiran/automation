package com.web.pages.flipkart;

import java.util.ArrayList;
import java.util.Collections;
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

	@FindBy(xpath="//div/div[.='TYPE OF SHOES']")
	private WebElement typeOfShoesBtn;

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
			//scrollToElement(typeOfShoesBtn, "Brand Filter");
			List<Boolean> brandStatus = new ArrayList<Boolean>();
			for(int i=0;i<Brands.size();i++) {
				click(brandBtn, "Brand");
				brandStatus.add(selectValueFromListOfWebElements(brand_Cbs, Brands.get(i)));
				Thread.sleep(2000);
			}
			if(brandStatus.stream().allMatch(val -> val == true))
				Status = true;
			else
				Status = false;
           Collections.sort(Brands);
			if(Status) {
				List<Boolean> filterStatus = new ArrayList<Boolean>();
				for(int i=0;i<Brands.size();i++) 
					filterStatus.add(verifySearchTextInListOfWebElements(selectedFilter_Text,Brands.get(i).toUpperCase()));
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
		click(minAndMaxPrice_DrpDwnBtns.get(0), "Minimum Price");
		Select selectMin = new Select(minAndMaxPrice_DrpDwnBtns.get(0));
		selectMin.selectByVisibleText(Min);
		click(minAndMaxPrice_DrpDwnBtns.get(1), "Maximum Price");
		Select selectMax = new Select(minAndMaxPrice_DrpDwnBtns.get(1));
		selectMax.selectByVisibleText(Max);
		String MinMax = Min+"-"+Max;
		if(verifySearchTextInListOfWebElements(selectedFilter_Text,MinMax)){
			logPaassed("Able to select the required Min & Max Price");
			return true;
		}else {
			logFailed("Unable to select the required Min & Max Price");
			return false;
		}
	}
}
