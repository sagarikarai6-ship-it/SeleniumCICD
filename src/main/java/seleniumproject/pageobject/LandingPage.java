package seleniumproject.pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage{
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//WebElement userPassword=driver.findElement(By.id("userPassword"));
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submitbutton;
	
	
	
	public void loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitbutton.click();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	
	
	
	
	
}