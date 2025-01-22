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

public class LoginTest {
	private WebDriver driver;
	private HomePage homepage;

	@BeforeTest(groups = "Login")
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
		homepage = new HomePage(driver);
	}

	@Test(groups = "Login", priority = 1)
	public void testLoginButtonVisibility() {
		Assert.assertTrue(homepage.isLoginButtonVisible(), "Login button is not visible. ");
	}

	@Test(groups = "Login", priority = 2)
	public void testLoginButtonClickability() {
		Assert.assertTrue(homepage.isLoginButtonClickable(), "Login button is not visible. ");
	}

	@Test(groups = "Login", priority = 3)
	public void verifyInvalidLogin() {
		homepage.login("abcdarasan.com", "guvi123");
		Assert.assertTrue(homepage.getErrorMessage().contains("Incorrect username or password"),
				"Error message for invalid login is not displayed.");
	}

	@Test(groups = "Login", priority = 4)
	public void verifyValidLogin() {
		try {
			homepage.login("guviminiproject2@gmail.com", "guvi123");
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(),
					"https://thinking-tester-contact-list.herokuapp.com/contactList",
					"User is not redirected to the contact list after valid login.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	 @AfterMethod(groups = "Login")
	    public void takeScreenshotOnFailure(ITestResult result) {
	        if (ITestResult.FAILURE == result.getStatus()) {
	            ScreenshotUtil.takeScreenshot(driver, result.getName());
	        }
	    }
	
	@AfterTest(groups="Login")
		public void tearDown() {
		if(driver !=null) {
			driver.quit();
		}
	}
}
