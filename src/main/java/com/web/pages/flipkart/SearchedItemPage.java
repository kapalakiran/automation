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
	private List<WebElement> brandCbs;

	@FindBy(xpath="//span[.='Price']/following::Select")
	private List<WebElement> minAndMaxPriceDrpDwnBtns;

	@FindBy(xpath="//div[text()='✕']/following-sibling::div")
	private List<WebElement> selectedFilterText;

	@FindBy(xpath="(//div[.='Brand'])[2]")
	private WebElement brandBtn;

	@FindBy(xpath="//div/div[.='TYPE OF SHOES']")
	private WebElement typeOfShoesBtn;

	@FindBy(xpath="//span[contains(text(),'Showing')]/following::div[@data-id]")
	private List<WebElement> itemTiles;
	
	@FindBy(css="input[placeholder='Search Brand']")
	private WebElement searchBrandTb;

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
			waitUntilElementFound(brandBtn);
			List<Boolean> brandStatus = new ArrayList<Boolean>();
			for(int i=0;i<Brands.size();i++) {
				click(brandBtn, "Brand");
				Thread.sleep(1000);
				enterText(searchBrandTb,Brands.get(i),"Brand");
				brandStatus.add(selectValueFromListOfWebElements(brandCbs, Brands.get(i)));
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
					filterStatus.add(verifySearchTextInListOfWebElements(selectedFilterText,Brands.get(i).toUpperCase()));
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
	 * @throws InterruptedException 
	 */
	public Boolean selectMinAndMaxAndVerifyItInFilterValues(String Min,String Max) throws InterruptedException {
		waitUntilElementFound(minAndMaxPriceDrpDwnBtns.get(0));
		click(minAndMaxPriceDrpDwnBtns.get(0), "Minimum Price");
		Select selectMin = new Select(minAndMaxPriceDrpDwnBtns.get(0));
		selectMin.selectByVisibleText(Min);
		click(minAndMaxPriceDrpDwnBtns.get(1), "Maximum Price");
		Select selectMax = new Select(minAndMaxPriceDrpDwnBtns.get(1));
		selectMax.selectByVisibleText(Max);
		String MinMax = Min+"-"+Max;
		if(verifySearchTextInListOfWebElements(selectedFilterText,MinMax)){
			logPassed("Able to select the required Min & Max Price");
			return true;
		}else {
			logFailed("Unable to select the required Min & Max Price");
			return false;
		}
	}
	/**
	 * @description To select first item tile
	 * @return
	 */
	public Boolean selectFirstTile() {
		return click(itemTiles.get(0), "1st Tile");
	}
	/**
	 * @description - To select the 1st tile and add to cart
	 * @return
	 */
	public Boolean selectItemAndAddToCart() {
		selectFirstTile();
		return new DetailedItemViewPage(driver).selectSizeAndAddToCart();
	}
}
