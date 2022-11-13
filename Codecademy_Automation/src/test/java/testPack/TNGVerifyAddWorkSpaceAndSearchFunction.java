package testPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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

import pages.HomePage;
import pages.LogInPage;
import pages.MyDirectoryPage;
import pages.SearchResultsPage;
import pages.SignUpPage;
import pages.WorkspacesPage;
import setup.Base;
import utils.Utility;

public class TNGVerifyAddWorkSpaceAndSearchFunction {
	
	private WebDriver driver ;
	private SignUpPage signUpPage;
	private LogInPage logInPage;
	private HomePage homePage ;
	private WorkspacesPage workspacesPage;
	private MyDirectoryPage myDirectoryPage;
	private SearchResultsPage searchResultsPage ;
	private int testID;
//	static ExtentTest test;
//	static ExtentHtmlReporter reporter;
	
	@Parameters ("browser")
	@BeforeTest
    public void launchTheBrowser(String browserName) {

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
		workspacesPage= new WorkspacesPage(driver);
		myDirectoryPage= new MyDirectoryPage(driver);
		searchResultsPage = new SearchResultsPage(driver);
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
	public void verifyAddWorkspace() {
		testID=103;
        homePage.clickNewWorkspaceButton();
		
		workspacesPage.clickJavaButton();
		
		boolean result = myDirectoryPage.visibilityOfMyDirectoryLink();
	    Assert.assertEquals(result, true); 
	    		
		myDirectoryPage.clickMyHome();
	}
	
	@Test
	public void verifySearchFunction() throws InterruptedException{
		Thread.sleep(3000);
		testID=104;
		homePage.clickSearchButton();
		homePage.enterAndSubmitSearchBox();
		
		boolean result= searchResultsPage.visibilityOfNumberOfCoursesText();
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
	public void clearObjects() 
	{
		signUpPage= null;
		logInPage= null;
		homePage = null;
		workspacesPage= null;
		myDirectoryPage= null;
		searchResultsPage = null;	
	}
	
	@AfterTest
	public void closeTheBrowser() {
		driver.close();
		driver=null;
		System.gc();
	}
}


