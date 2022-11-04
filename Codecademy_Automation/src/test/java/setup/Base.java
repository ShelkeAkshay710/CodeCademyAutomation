package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {
	
	public static WebDriver openChromeBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Akshay Shelke\\OneDrive\\Documents\\AutomationSE\\Selenium\\chromedriver_win32\\12 oct 2022\\chromedriver.exe");	
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	public static WebDriver openEdgeBrowser() {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\Akshay Shelke\\OneDrive\\Documents\\AutomationSE\\Selenium\\EdgeDriver\\msedgedriver.exe");	
		WebDriver driver = new EdgeDriver();		
		return driver;
	}

}
