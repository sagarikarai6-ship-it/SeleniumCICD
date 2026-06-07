package seleniumproject.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumproject.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
WebDriver driver;
	
	public ProductCatalog(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	//List<WebElement> products = driver.findElements(By.cssSelector(".card"));
	@FindBy(css=".card")
	List<WebElement> products;
	
	By waitlocator1=By.cssSelector(".container");
	By waitlocator2=By.cssSelector(".ng-animating");
	By waitlocator3=By.cssSelector(".ng-animating");
	By waitlocator4=By.cssSelector(".ngx-spinner-overlay");
	By waitlocator5=By.cssSelector("#toast-container");
	By waitlocator6=By.xpath("//button[contains(text(),'Cart')]");
	By addtocart= By.xpath(".//button[contains(text(), 'Add To Cart')]");
	
	public List<WebElement> getProductList() {
		
		waitForVisibilityOfElement(waitlocator1);
		waitForInVisibilityOfElement(waitlocator2);
		return products;
		
	}
	
	public WebElement getProductbyName(String ProductName) {
		WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().trim()
				.equalsIgnoreCase(ProductName)).findFirst().orElseThrow(() -> new RuntimeException("Product not found"));
		return prod;
	}
	
	public void addProducttoCart(String ProductName) {
		WebElement prod= getProductbyName(ProductName);
		prod.findElement(addtocart).click();
		waitForInVisibilityOfElement(waitlocator3);
		//waitForInVisibilityOfElement(waitlocator4);
		//waitForInVisibilityOfElement(waitlocator5);
		
	}
	
	public void clickCart() {
		WebElement cartButton = waitForElementtobeClickable(waitlocator6);
		cartButton.click();
	}
	
}
