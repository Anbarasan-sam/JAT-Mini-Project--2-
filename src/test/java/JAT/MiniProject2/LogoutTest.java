package JAT.MiniProject2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ScreenshotUtil;

public class LogoutTest {
    private WebDriver driver;
    private HomePage homePage;
    private LogoutPage logoutPage;

    @BeforeTest(groups="Logout")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        homePage = new HomePage(driver);
        logoutPage = new LogoutPage(driver);

        homePage.login("guvi1234@gmail.com", "guvi123"); 
    }

    @Test(priority = 1,groups="Logout")
    public void testLogoutButtonIsVisible() {
        Assert.assertTrue(logoutPage.isLogoutButtonVisible(), "Logout button is not visible.");
    }

    @Test(priority = 2,groups="Logout")
    public void testLogoutRedirectsToLoginPage() {
        logoutPage.clickLogoutButton();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://thinking-tester-contact-list.herokuapp.com/"), "Logout did not redirect to the login page.");
    }
    @AfterMethod(groups="Logout")
    public void takeScreenshotOnFailure(ITestResult result) {
    	if(ITestResult.FAILURE==result.getStatus()) {
    		ScreenshotUtil.takeScreenshot(driver, result.getName());
    	}
    }
    

    @AfterTest(groups="Logout")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
