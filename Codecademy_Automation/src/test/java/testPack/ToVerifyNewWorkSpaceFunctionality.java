package testPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;
import pages.LogInPage;
import pages.MyDirectoryPage;
import pages.SearchResultsPage;
import pages.SignUpPage;
import pages.WorkspacesPage;
import utils.Utility;

public class ToVerifyNewWorkSpaceFunctionality {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Akshay Shelke\\OneDrive\\Documents\\AutomationSE\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.codecademy.com/");
		driver.manage().window().maximize();
		
		SignUpPage signUpPage= new SignUpPage(driver);
		signUpPage.clickLogInOption();
		
		LogInPage logInPage= new LogInPage(driver);
		String data=Utility.getDataFromExcelSheet("Sheet2", 1, 0);
		logInPage.sendEmailOrUsername(data);
		
		data=Utility.getDataFromExcelSheet("Sheet2", 1, 1);
		logInPage.sendPassword(data);
		logInPage.clicklogInButton();
		
		HomePage homePage = new HomePage(driver);
		homePage.clickNewWorkspaceButton();
		
		WorkspacesPage workspacesPage= new WorkspacesPage(driver);
		workspacesPage.clickJavaButton();
		
		MyDirectoryPage myDirectoryPage= new MyDirectoryPage(driver);
		boolean result = myDirectoryPage.visibilityOfMyDirectoryLink();
		
		if(result==true)
		{
			System.out.println("New Workspace- PASS");
		}
		else {
			System.out.println("New Workspace- FAIL");
		}
		
 }
}