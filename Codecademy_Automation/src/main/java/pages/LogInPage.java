package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
	
	@FindBy(xpath="//input[@name='user[login]']")
	private WebElement emailOrUsername;

	@FindBy(xpath="//input[@name='user[password]']")
	private WebElement password;

	@FindBy(xpath="//button[text()='Log in']")
	private WebElement logInButton;
	
	
	public LogInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void sendEmailOrUsername(String userName) {
		emailOrUsername.sendKeys(userName);
	}
	
	public void sendPassword(String passWord) {
		password.sendKeys(passWord);
	}
	
	public void clicklogInButton() {
		logInButton.click();
	}

	public boolean clicklogInButton(boolean result) {
		result= logInButton.isEnabled();
		return result;
	}

	
}
