package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private WebDriver driver;
	private Actions act;
	
	@FindBy(xpath="//a[text()='Edit']")
	private WebElement editButton;
	
	@FindBy(xpath="//a[@aria-label='Create New Workspace']")
	private WebElement newWorkspaceButton;
	
	@FindBy(xpath="//button[@aria-label='Search Codecademy Content']")
	private WebElement searchButton;
	
	@FindBy(xpath="//input[@id='header-search-bar']")
	private WebElement searchBox;
	
	@FindBy(xpath="(//ul[@role='menubar']//li)[20]")
	private WebElement mainNavigationButton;
	
	@FindBy(xpath="//a[text()='Log Out']")
	private WebElement logOut;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		act= new Actions(driver);
	}

	public void clickEditLink() {
		editButton.click();
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	public void clickNewWorkspaceButton() {
		newWorkspaceButton.click();
	}
	
	public void enterAndSubmitSearchBox() {
		searchBox.sendKeys("Software testing");
		searchBox.submit();
	}

	public void clickMainNavigationButton() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ul[@role='menubar']//li)[20]")));
		act.moveToElement(mainNavigationButton).perform();
		mainNavigationButton.click();
	}

	public void clickLogOut() {
		logOut.click();
	}

}



