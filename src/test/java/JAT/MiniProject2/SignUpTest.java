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

public class SignUpTest {
	private WebDriver driver;
	private HomePage homepage;
	
	@BeforeTest(groups="Signup")
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
		homepage = new HomePage(driver);
	}

	@Test(groups="Signup",priority=1)
	public void testSignUpBtnVisibility() {
		
		Assert.assertTrue(homepage.isSignUpBtnVisible(),"Signup button is not visible.");
	}
	
	@Test(groups="Signup",priority=2)
	public void testSignUpBtnClickability() {
		
		Assert.assertTrue(homepage.isSignUpBtnClickable(),"Signup button is not clickable.");
	}
	
	@Test(groups="Signup",priority=3)
	public void testHomePageRedirectsToAddUserPage() {
		
		homepage.clickSignUpBtn();
		
		if(driver.getTitle().equalsIgnoreCase("Add User")) {
			System.out.println(" After clicking signup button redirects to Add use page.");
		}else {
			System.out.println(" After clicking signup button redirects to wrong page.");
		}
	}
	
	 @AfterMethod(groups = "Signup")
	    public void takeScreenshotOnFailure(ITestResult result) {
	        if (ITestResult.FAILURE == result.getStatus()) {
	            ScreenshotUtil.takeScreenshot(driver, result.getName());
	        }
	    }
	
	@AfterTest(groups="Signup")
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
