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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactDisplayTest {
    private WebDriver driver;
    private HomePage homePage;
    private ContactPage contactPage;

    @BeforeTest(groups = "Contact Display")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
        homePage = new HomePage(driver);
        contactPage = new ContactPage(driver);

        homePage.login("guvi1234@gmail.com", "guvi123");
    }

    @Test(priority = 1,groups = "Contact Display")
    public void testContactDetailsDisplayedCorrectly() {
        contactPage.clickAddContact();
        contactPage.fillContactDetails("John", "Doe", "john.doe@example.com", "9685743214", "1990-01-01");
        contactPage.enterStreetAdd1("123 Main St");
        contactPage.enterStreetAdd2("Apt 4B");
        contactPage.enterCity("New York");
        contactPage.enterState("NY");
        contactPage.enterPostalCode("10001");
        contactPage.enterCountry("USA");
        contactPage.submitContact();

        boolean isContactFound = contactPage.isContactDisplayed("John", "Doe", "9685743214");
        Assert.assertTrue(isContactFound, "Contact details are not displayed correctly.");
    }


    @Test(priority = 2,groups = "Contact Display")
    public void testContactsSortedByLastName() {
        List<String> lastNames = contactPage.getAllLastNames();
        List<String> sortedLastNames = new ArrayList<>(lastNames);
        Collections.sort(sortedLastNames);
        
    }

    @Test(priority = 3,groups = "Contact Display")
    public void testPhoneNumberDisplay() {
        List<String> phoneNumbers = contactPage.getAllPhoneNumbers();
        for (String phoneNumber : phoneNumbers) {
            Assert.assertFalse(phoneNumber.startsWith("+91"), "Phone number not have the +91 extension.");
        }
    }
    
    @AfterMethod(groups = "Contact Display")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }

    @AfterTest(groups = "Contact Display")
    public void tearDown() {
            driver.quit();
        }
}
