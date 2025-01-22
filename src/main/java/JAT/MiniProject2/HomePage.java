package JAT.MiniProject2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BaseClass;

public class HomePage extends BaseClass{
	
	private WebDriver driver;
	
	@FindBy(id="signup")
	private WebElement signupButton;
	
	@FindBy(id="submit")
	private WebElement loginButton;
	
	@FindBy(id="email")
	private WebElement emailField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	@FindBy(id = "error")
    private WebElement errorMessage;
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean isSignUpBtnVisible() {
		return signupButton.isDisplayed();
	}
	
	public boolean isSignUpBtnClickable() {
		return signupButton.isEnabled();
	}
	
	public void clickSignUpBtn() {
		isSignUpBtnVisible();
		isSignUpBtnClickable();
		signupButton.click();
	}
	
	public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }

    public boolean isLoginButtonClickable() {
        return loginButton.isEnabled();
    }

    public void login(String email, String password) {
    	waitForElementVisible(emailField, 10);
    	waitForElementClickable(emailField, 10);
        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
    	waitForElementVisible(errorMessage, 10);
        return errorMessage.getText();
    }
	
}
