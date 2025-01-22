package JAT.MiniProject2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BassTest {
	
	private WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
	}
	
	@AfterTest
	public void tearDown() {
		if(driver !=null) {
			driver.quit();
		}
	}

}
