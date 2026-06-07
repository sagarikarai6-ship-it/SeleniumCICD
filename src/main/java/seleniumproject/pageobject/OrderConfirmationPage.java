package seleniumproject.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import seleniumproject.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By locator1=By.cssSelector(".hero-primary");
	By locator2=By.cssSelector(".content-wrap");
	
	
	
	public void thankyouMessage() {
		WebElement confirmationMessage = waitForVisibilityOfElement(locator1);
		String actualMessage = confirmationMessage.getText().trim();
		Assert.assertTrue(actualMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	public String extractOrderID() {
		WebElement orderIdElement = waitForVisibilityOfElement(locator2);
		String rawText = orderIdElement.getText();
		String orderIdLine = java.util.Arrays.stream(rawText.split("\n")).filter(line -> line.contains("|"))
			    .findFirst().orElseThrow(() -> new RuntimeException("Order ID line not found"));
		String OrderID= orderIdLine.replace("|"," ").trim();
		System.out.println("Order ID is : "+ OrderID);
		return OrderID;
	}
}
