package com.web.pages.locus;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.util.BaseFunctions;
import com.util.TestDataGenerator;

public class LiveViewPage extends BaseFunctions{

	@FindBy(css="div[class^=UserMenuItem__item]")
	private WebElement profileIcon;

	@FindBy(css="div[class^='UserMenuItem__title']")
	private WebElement userTitleDrpDwn;

	@FindBy(css="ul.UserMenuItem__verticalMenu___1Chty")
	private WebElement userMenuDropdown;

	@FindBy(css="span[class*=SearchTheme__icon]")
	private WebElement searchThemeIcon;

	@FindBy(css="div[class*='TaskSearchView__overlay_'] div[class*=theme__input] input")
	private WebElement searchTbInPopup;

	@FindBy(css="div[class*=TaskSearchResults__tableWrap] div[class*='TaskSearchResults__taskIdsWrap']")
	private List<WebElement> searchResultsDrpDwns;

	@FindBy(css="button[testid='addVisit']")
	private WebElement addVisitBtn;

	@FindBy(css="input[testid=enterVistId]")
	private WebElement taskIDTf;

	@FindBy(css="div[testid*='selectTeam'] div[class*=cardTitle]")
	private WebElement selectTeamBtn;

	@FindBy(css="div[data-test-id='select-selectMenu']")
	private List<WebElement> selectMenuDrpDwns;

	@FindBy(css="button[testid=proceedTaskCreation]")
	private WebElement proceedBtn;

	@FindBy(css="div[class*=ListCard__left] div[class*=VisitTypeSelector__title]")
	private List<WebElement> selectVisitTypeBtns;

	@FindBy(css="div[class*=HomebaseSelectorWithRenderer__selectInputContainer] input")
	private WebElement addressTb;

	@FindBy(css="button[class*=SlotCard__button]")
	private WebElement chooseSlotBtn;

	@FindBy(css="div[class*=SlotCard__deliverNow] input")
	private WebElement enterSLABtn;

	@FindBy(css="button[testid=save]")
	private WebElement saveBtn;

	@FindBy(css="button[testid= createTask]")
	private WebElement createTaskBtn;

	@FindBy(css="div[class*=TaskAssignedOverlay__title]")
	private WebElement taskCreatedBtn;

	@FindBy(css="span[class^=TaskAssignedOverlay]+span")
	private WebElement taskID;
	
	public LiveViewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	/**
	 * @author kirankumar
	 * @description - to hover on profile & verify user menu dropdown
	 * @return
	 * @throws InterruptedException 
	 */
	public Boolean hoverOnProfileAndVerifyUserMenuDropDowns(String UserName) throws InterruptedException{
		waitForProfileIcon();
		mouseHover(profileIcon);
		customWait(2000);
		if(isElementPresent(userMenuDropdown)) {
			return userTitleDrpDwn.getText().equals(UserName);
		}
		return false;
	}
	
	public void waitForProfileIcon() {
		waitUntilElementFound(profileIcon);
	}
	/**
	 * @author kirankumar 
	 * @description to search & verify its drop down
	 * @param text
	 * @return
	 */
	public Boolean searchAndVerifyItsDrpDwn(String text) {
		clickUsingJavaScript(searchThemeIcon);
		enterText(searchTbInPopup, text, "Search");
		if(searchResultsDrpDwns.size()==1) {
			logPassed("Result count is verified");
			return true;
		}else
			logFailed("Failed to verify Result count");
		return false;
	}
	/**
	 * @author kirankumar
	 * @description - To select visit type
	 * @param text
	 * @return
	 * @throws InterruptedException 
	 */
	public Boolean selectVisitType(String text) throws InterruptedException {
		waitUntilElementFound(selectVisitTypeBtns.get(0));
		click(selectVisitTypeBtns.get(0),"Visit Type");
		customWait(1000);
		selectUsingText(text);
		click(selectVisitTypeBtns.get(1),"Visit Type");
		customWait(1000);
		return selectUsingText(text);
	}
	/**
	 * @author kirankumar 
	 * @description - to add visit
	 * @return
	 * @throws InterruptedException 
	 */
	public Boolean selectAddVisitBtn() throws InterruptedException {
		click(addVisitBtn, "Add Visit");
		enterText(taskIDTf,TestDataGenerator.getRandomNumberBetweenRange(10000, 100000)+"","");
		click(selectTeamBtn,"Select Team");
		click(selectMenuDrpDwns.get(0), "Select Menu Drop Down");
		Boolean Status = click(proceedBtn, "Proceed");
        customWait(2500);
		return Status;
	}

	/**
	 * @author kirankumar
	 * @description - to add task 
	 * @param address
	 * @throws InterruptedException 
	 */
	public String addTask(String address,String visitType,String SLA) throws InterruptedException {
		String tasKID = "";
		if(selectAddVisitBtn()) {
			selectVisitType(visitType);
			enterText(addressTb,address,"Address");
			customWait(2000);
			click(selectMenuDrpDwns.get(0), "select");
			click(chooseSlotBtn, "Choose Slot");
			enterText(enterSLABtn,SLA,"SLA");
			click(saveBtn, "Save");
			customWait(2000);
			click(createTaskBtn, "Create Task");
			if(isElementPresent(taskCreatedBtn)) {
				tasKID = taskID.getText();
			}
		}
		return tasKID;
	}
}
