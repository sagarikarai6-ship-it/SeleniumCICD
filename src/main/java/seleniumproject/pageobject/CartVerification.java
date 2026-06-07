package seleniumproject.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import seleniumproject.AbstractComponents.AbstractComponent;

public class CartVerification extends AbstractComponent {
	
		WebDriver driver;
			
			public CartVerification(WebDriver driver){
				super(driver);
				this.driver=driver;
				PageFactory.initElements(driver, this);
			}
			@FindBy(xpath=".//div[@class='cartSection']")
			List<WebElement> CartProducts;
			
	By waitlocator1= By.xpath("//div[@class='cartSection']");
	By waitlocator2= By.xpath("//button[contains(text(),'Checkout')]");
	By checkoutbut=By.xpath("//button[contains(text(),'Checkout')]");
	By countryselect=By.cssSelector("[placeholder='Select Country']");
	
	
	
	
	public void cartCheck(String ProductName) {
		waitForVisibilityOfElement(waitlocator1);
		Boolean match = CartProducts.stream().anyMatch(CartProduct -> CartProduct.getText().contains(ProductName));
		Assert.assertTrue(match);
	}
	public void checkOut(String Country) {
		waitForVisibilityOfElement(waitlocator2);
		Actions a= new Actions(driver);
		a.moveToElement(driver.findElement(checkoutbut)).click().perform();
		a.sendKeys(driver.findElement(countryselect),Country).build().perform();
	}
	
			
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cartSection']")));
	//List<WebElement> CartProducts= driver.findElements(By.xpath(".//div[@class='cartSection']"));
	//Boolean match = CartProducts.stream().anyMatch(CartProduct -> CartProduct.getText().contains(ProductName));
	//Assert.assertTrue(match);

}
