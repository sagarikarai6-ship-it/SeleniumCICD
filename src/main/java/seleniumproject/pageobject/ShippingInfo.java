package seleniumproject.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumproject.AbstractComponents.AbstractComponent;

public class ShippingInfo extends AbstractComponent {
	WebDriver driver;
	
	public ShippingInfo(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[contains(@class,'ta-item')] [contains(.,'France')]")
	List<WebElement> options;
	
	By countryOptionsLocator = By.xpath("//button[contains(@class,'ta-item')]");
	By locator1=By.className("ta-results");
	By locator2=By.cssSelector(".action__submit");
	
	public void countrydropdown(String Country) {
		waitForVisibilityOfElement(locator1);
		List<WebElement> dynamicOptions = driver.findElements(countryOptionsLocator);
		WebElement targetCountry = dynamicOptions.stream()
				.filter(c -> c.getText().trim().toLowerCase().contains(Country.trim().toLowerCase())).findFirst()
				.orElseThrow(() -> new RuntimeException("Country not found: " + Country));
		Actions a= new Actions(driver);
		a.click(targetCountry).perform();
	}
	
	public void submitButton() {
		waitForVisibilityOfElement(locator2);
		Actions a= new Actions(driver);
		a.moveToElement(driver.findElement(locator2)).click().perform();
	}
	
}
	

