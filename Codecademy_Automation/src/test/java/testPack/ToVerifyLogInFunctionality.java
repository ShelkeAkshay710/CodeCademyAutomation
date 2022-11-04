package testPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;
import pages.LogInPage;
import pages.SignUpPage;
import utils.Utility;

public class ToVerifyLogInFunctionality extends Utility{
	
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
	
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Akshay Shelke\\OneDrive\\Documents\\AutomationSE\\Selenium\\chromedriver_win32\\12 oct 2022\\chromedriver.exe");
	
	WebDriver driver = new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	driver.get("https://www.codecademy.com/");
	driver.manage().window().maximize();
	
	SignUpPage signUpPage= new SignUpPage(driver);
	signUpPage.clickLogInOption();
	
	int testID=1;
	LogInPage logInPage= new LogInPage(driver);
	String data=Utility.getDataFromExcelSheet("Sheet2", 1, 0);
	logInPage.sendEmailOrUsername(data);
	
	data=Utility.getDataFromExcelSheet("Sheet2", 1, 1);
	logInPage.sendPassword(data);
	logInPage.clicklogInButton();
	
	String currentUrl= driver.getCurrentUrl();
	String expectedUrl= "https://www.codecademy.com/login?redirect=https%3A%2F%2Fwww.codecademy.com%2F";
	System.out.println(currentUrl);
	System.out.println(expectedUrl);
  
	Utility.captureScreen(testID, driver);
	
	if(currentUrl.equals(expectedUrl))
	{
		System.out.println("PASS");
	}
	else
	{
		System.out.println("FAIL");
	}	
	
	HomePage homepage= new HomePage(driver);
	homepage.clickMainNavigationButton();
	homepage.clickLogOut();
	
  }
}