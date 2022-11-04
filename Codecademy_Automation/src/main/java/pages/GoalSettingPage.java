package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoalSettingPage {
	
	private Actions act;
	
	@FindBy(xpath="//label[text()='Change my career']")
	private WebElement changeMyCareerButton;
	
	@FindBy(xpath="(//span[@class='gamut-56w5ya-HexagonHidden e17b5ixy1'])[5]")
	private WebElement weeklyLearningTargetDaySix;
	
	@FindBy(xpath="//button[text()='Save goal settings']")
	private WebElement saveMyGoalButton;
	
	@FindBy(xpath="//div[text()='Your goals have been updated.']")
	private WebElement goalHavebeenSavedPopup;
	
	public GoalSettingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		act= new Actions(driver);
	}
	
	public void changeMyCareerButton() {
		changeMyCareerButton.click();
	}
			
	public void clickSixthHexagonSymbol() {
		act.moveToElement(weeklyLearningTargetDaySix).click().perform();
	}
	
	public void clickSaveMyGoalButton() {
		saveMyGoalButton.click();
	}
	
	public Boolean verifyGoalHavebeenSavedPopup() {
		Boolean result= goalHavebeenSavedPopup.isDisplayed();
		return result;
		
	}
	
}
