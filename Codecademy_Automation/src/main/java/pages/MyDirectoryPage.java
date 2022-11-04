package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyDirectoryPage {
	
	@FindBy(xpath="//a[text()='my_directory / ']")
	private WebElement myDirectoryLink;
	
	@FindBy(xpath="//a[text()='My Home']")
	private WebElement myHome;
	
	
	
	public MyDirectoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean visibilityOfMyDirectoryLink() {
		
		Boolean result=myDirectoryLink.isDisplayed();
		return result;
	}
	
	public void clickMyHome() {
        myHome.click();
	}

}
