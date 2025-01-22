package JAT.MiniProject2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BaseClass;

public class ContactPage extends BaseClass {
    protected WebDriver driver;

    @FindBy(id = "add-contact")
    private WebElement addContactButton;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "birthdate")
    private WebElement birthDateField;

    @FindBy(id = "street1")
    private WebElement streetAdd1;

    @FindBy(id = "street2")
    private WebElement streetAdd2;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "stateProvince")
    private WebElement state;

    @FindBy(id = "postalCode")
    private WebElement postalCode;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;
    
    @FindBy(xpath="(//tr//td)[2]")
    private WebElement contactDetailsName;

    @FindBy(id = "error")
    private WebElement errorMessage;

    @FindBy(xpath = "//table//tr[@class='contactTableBodyRow']")
    private List<WebElement> contactRows;
    
    @FindBy(xpath = "//table//tr//td[2]")
    private List<WebElement> lastNameFields;

    @FindBy(xpath = "//table//tr//td[5]")
    private List<WebElement> phoneNumberFields;

    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddContact() {
        waitForElementVisible(addContactButton, 10);
        waitForElementClickable(addContactButton, 10);
        addContactButton.click();
    }

    public void fillContactDetails(String firstName, String lastName, String email, String phone, String birthDate) {
        if (firstName != null) firstNameField.sendKeys(firstName);
        if (lastName != null) lastNameField.sendKeys(lastName);
        if (email != null) emailField.sendKeys(email);
        if (phone != null) phoneField.sendKeys(phone);
        if (birthDate != null) birthDateField.sendKeys(birthDate);
    }

    public void enterStreetAdd1(String streetAdd) {
        waitForElementClickable(streetAdd1, 10);
        streetAdd1.sendKeys(streetAdd);
    }

    public void enterStreetAdd2(String streetAdd) {
        waitForElementClickable(streetAdd2, 10);
        streetAdd2.sendKeys(streetAdd);
    }

    public void enterCity(String cityName) {
        city.sendKeys(cityName);
    }

    public void enterState(String stateName) {
        state.sendKeys(stateName);
    }

    public void enterPostalCode(String pincode) {
        postalCode.sendKeys(pincode);
    }

    public void enterCountry(String countryName) {
        country.sendKeys(countryName);
    }

    public void submitContact() {
        waitForElementVisible(submitButton, 10);
        submitButton.click();

    }

    
    public String contactDetailsName() {
    	waitForElementVisible(contactDetailsName, 10);
        return contactDetailsName.getText();
    }

    public void clickContact() {
    	waitForElementVisible(contactDetailsName, 10);
    	waitForElementClickable(contactDetailsName, 10);
        contactDetailsName.click();
        
    }
    
    public List<WebElement> getContactRows() {
        return contactRows;
    }
    
    public String getErrorMessage() {
        waitForElementVisible(errorMessage, 10);
        return errorMessage.getText();
    }

    public List<String> getAllContactDetails() {
        List<String> contactDetails = new ArrayList<>();
        for (WebElement row : contactRows) {
            contactDetails.add(row.getText());
        }
        return contactDetails;
    }
    
    public void debugPrintAllRows() {
        for (WebElement row : contactRows) {
            System.out.println("Row Text: " + row.getText());
        }
    }

    public List<String> getAllLastNames() {
        List<String> lastNames = new ArrayList<>();
        for (WebElement lastName : lastNameFields) {
            lastNames.add(lastName.getText().trim());
        }
        return lastNames;
    }

    public List<String> getAllPhoneNumbers() {
        List<String> phoneNumbers = new ArrayList<>();
        for (WebElement phoneNumber : phoneNumberFields) {
            phoneNumbers.add(phoneNumber.getText().trim());
        }
        return phoneNumbers;
    }

    public boolean isContactDisplayed(String firstName, String lastName, String phoneNumber) {
    	waitForElementsVisible(contactRows, 10);
        for (WebElement row : contactRows) {
            if (row.getText().contains(firstName) &&
                row.getText().contains(lastName) &&
                row.getText().contains(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
    
    public void waitFor(BooleanSupplier condition, int timeoutInSeconds, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            wait.until(driver -> condition.getAsBoolean());
        } catch (TimeoutException e) {
            throw new AssertionError(errorMessage, e);
        }
    }

}
