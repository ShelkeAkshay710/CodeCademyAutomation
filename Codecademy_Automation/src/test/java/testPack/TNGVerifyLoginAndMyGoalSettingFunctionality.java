package testPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pages.GoalSettingPage;
import pages.HomePage;
import pages.LogInPage;
import pages.SignUpPage;
import setup.Base;
import utils.Utility;

public class TNGVerifyLoginAndMyGoalSettingFunctionality {
	
	private WebDriver driver ;
	private SignUpPage signUpPage;
	private LogInPage logInPage;
	private HomePage homePage ;
	private GoalSettingPage goalSettingPage; 
	private int testID;
//	static ExtentTest test;
//	static ExtentHtmlReporter reporter;
	
	@Parameters ("browser")
	@BeforeTest
    public void launchTheBrowser(String browserName) {
	    
//		reporter = new ExtentHtmlReporter("test-output/ExtendReport/Extent.html");
//		ExtentReports extend = new ExtentReports();
//		extend.attachReporter(reporter);

		if(browserName.equals("Chrome")) 
		{
			driver=Base.openChromeBrowser();
		}
		
	    if(browserName.equals("Edge")) 
	    {
            driver=Base.openEdgeBrowser(); 
	    }
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	     	driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void createPOMObjects() {
		signUpPage= new SignUpPage(driver);
		logInPage= new LogInPage(driver);
		homePage = new HomePage(driver);
		goalSettingPage = new GoalSettingPage(driver);
		
	}
	
	@BeforeMethod
	public void logIn() throws EncryptedDocumentException, IOException {
		driver.get("https://www.codecademy.com/");
		
		signUpPage.clickLogInOption();
		
		String data=Utility.getDataFromExcelSheet("Sheet2", 1, 0);
		logInPage.sendEmailOrUsername(data);
		
		data=Utility.getDataFromExcelSheet("Sheet2", 1, 1);
		logInPage.sendPassword(data);
		
		logInPage.clicklogInButton();
	}
	
	@Test
	public void verifyLogin() {
		testID=101;
		String currentUrl= driver.getCurrentUrl();
		String expectedUrl= "https://www.codecademy.com/login?redirect=https%3A%2F%2Fwww.codecademy.com%2F";
		System.out.println(currentUrl);
		System.out.println(expectedUrl);
	    Assert.assertEquals(currentUrl, expectedUrl); 
	}
	
	@Test
	public void verifyMyGoalSetting() {
		testID=102;
		homePage.clickEditLink();
		
		
		goalSettingPage.changeMyCareerButton();
		goalSettingPage.clickSixthHexagonSymbol();
		goalSettingPage.clickSaveMyGoalButton();
	    		
		boolean result = goalSettingPage.verifyGoalHavebeenSavedPopup();
		Assert.assertEquals(result, true); 
}

	@AfterMethod 
	public void logOut(ITestResult result) throws IOException {
		if(ITestResult.FAILURE==result.getStatus()) 
		{
		  Utility.captureScreen(testID, driver);	
		}
			
		homePage.clickMainNavigationButton();
		homePage.clickLogOut(); 
	}

	@AfterClass
	public void clearObjects() {
		signUpPage= null;
		logInPage= null;
		homePage = null;
		goalSettingPage =null;	
		}
	
	@AfterTest
	public void closeTheBrowser() {
		driver.close();
		driver=null;
		System.gc();	
	}
	
}
