package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
	
	@FindBy(xpath="//span[contains(text(),'Showing')]")
	private WebElement numberOfCoursesText;
	
	public SearchResultsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public boolean visibilityOfNumberOfCoursesText() {
		
       boolean result = numberOfCoursesText.isDisplayed();
		return result;
	}

}
