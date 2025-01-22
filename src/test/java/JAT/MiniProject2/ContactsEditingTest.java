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

public class ContactsEditingTest {
	private WebDriver driver;
	private HomePage homePage;
	private ContactPage contactPage;
	private ContactEditingPage editingPage;
	
	@BeforeTest(groups = "Edit Contact")
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
		homePage = new HomePage(driver);
		contactPage = new ContactPage(driver);
	    editingPage = new ContactEditingPage(driver);

	    homePage.login("guviminiproject2@gmail.com", "guvi123");
	}
	@Test(groups = "Edit Contact")
	public void testRedirectsToContactDetailsPage() {
		
		contactPage.clickContact();
	
		Assert.assertTrue(driver.getCurrentUrl().contains("contactDetails"), "Not redirected to contact details page.");
	}
	
	 @Test(priority = 2,groups = "Edit Contact")
	    public void testEditContactDetails() throws InterruptedException {
		 	Thread.sleep(2000);
	        editingPage.clickEditButton();

	        String updatedFirstName = "JohnUpdated";
	        String updatedLastName ="DoeUpdated";
	        String updatedEmail = "john.updated@example.com";
	        String updatedPhNum = "9876543210";

	        Thread.sleep(2000);
	        editingPage.updateContactDetails(updatedFirstName, updatedLastName, updatedEmail, updatedPhNum);
	        editingPage.submitEditedContact();

	 }

	    @Test(priority = 3,groups = "Edit Contact")
	    public void testFieldChangesDontAffectOtherFields() throws InterruptedException {
	    	Thread.sleep(2000);
	    	editingPage.clickEditButton();

	        String originalFirstName = "John";
	        String originalLastName = "Doe";
	        editingPage.updateContactDetails(originalFirstName, "Smith", null, null);  // Only update last name

	        editingPage.submitEditedContact();
	        Thread.sleep(2000);
	        Assert.assertFalse(driver.getPageSource().contains("Doe"), "Original last name should not appear.");
	    }

	    @Test(priority = 4,groups = "Edit Contact")
	    public void verifySaveWithEmptyFields() {
	    	editingPage.clickEditButton();

	    	editingPage.updateContactDetails(null, null, null, null);  

	    	editingPage.submitEditedContact();

	        String errorMessage = editingPage.getErrorMessage();
	        Assert.assertFalse(errorMessage.isEmpty(), "Should not allow saving empty fields.");
	    }
	    
	    @AfterMethod(groups = "Edit Contact")
	    public void takeScreenshotOnFailure(ITestResult result) {
	        if (ITestResult.FAILURE == result.getStatus()) {
	            ScreenshotUtil.takeScreenshot(driver, result.getName());
	        }
	    }

	    @AfterTest(groups = "Edit Contact")
	    public void tearDown() {
	            driver.quit();
	        }
	
}
