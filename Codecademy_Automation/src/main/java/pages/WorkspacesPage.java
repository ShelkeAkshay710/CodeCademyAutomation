package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkspacesPage {
	
	@FindBy(xpath="(//div[contains(@class,'gamut-aa')])[11]")
	private WebElement javaButton;
	
	public WorkspacesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickJavaButton() {
		javaButton.click();
	}

}
