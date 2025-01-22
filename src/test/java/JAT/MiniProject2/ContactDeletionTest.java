package JAT.MiniProject2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ScreenshotUtil;

public class ContactDeletionTest {
	private WebDriver driver;
	private HomePage homePage;
	private ContactDeletionPage contactDeletionPage;
	private ContactPage contactPage;

	@BeforeTest(groups="Contact Delete")
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
		homePage = new HomePage(driver);
		contactPage = new ContactPage(driver);
		contactDeletionPage = new ContactDeletionPage(driver);

		homePage.login("guvi1234@gmail.com", "guvi123");
	}

	@Test(priority = 1,groups="Contact Delete")
	public void testDeleteContactShowsConfirmationAlert() {
		contactPage.clickContact();
		contactDeletionPage.deleteContact();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test(priority = 2,groups="Contact Delete")
	public void testDeleteSingleContact() throws InterruptedException {
		Thread.sleep(2000);
		String contactToDelete = "John Doe";

		Assert.assertTrue(contactDeletionPage.isContactPresentClick(contactToDelete), "Contact to delete not found.");

		Thread.sleep(2000);
		contactDeletionPage.deleteContact();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test(priority = 3,groups="Contact Delete")
	public void testDeleteContactDoesNotAffectOtherContacts() throws InterruptedException {
		Thread.sleep(2000);
		String contactToDelete = "Anbarasan z";

		Assert.assertTrue(contactDeletionPage.isContactPresentClick(contactToDelete), "Contact to delete not found.");

		contactDeletionPage.deleteContact();
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Thread.sleep(2000);
		Assert.assertTrue(contactDeletionPage.isContactPresent("John Doe"),
				"Other contacts were affected by the deletion.");
	}
	@AfterMethod(groups = "Contact Delete")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }

    @AfterTest(groups = "Contact Delete")
    public void tearDown() {
            driver.quit();
        }

}
