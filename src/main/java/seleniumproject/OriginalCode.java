package seleniumproject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class OriginalCode {

	public static void main(String[] args) throws Exception {
		String ProductName="ZARA COAT 3";
		String Country ="France";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Login page
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().window().maximize();
		
	
		driver.findElement(By.id("userEmail")).sendKeys("sagatest@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@1");		
		driver.findElement(By.id("login")).click();

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //ProductHome page
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		List<WebElement> products = driver.findElements(By.cssSelector(".card"));
		WebElement prod = products.stream()
		    .filter(product -> product.findElement(By.cssSelector("b")).getText().trim().equalsIgnoreCase(ProductName))
		    .findFirst().orElseThrow(() -> new RuntimeException("Product not found"));
		prod.findElement(By.xpath(".//button[contains(text(), 'Add To Cart')]")).click();		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Cart')]")));
		cartButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cartSection']")));
		List<WebElement> CartProducts= driver.findElements(By.xpath(".//div[@class='cartSection']"));
		Boolean match = CartProducts.stream().anyMatch(CartProduct -> CartProduct.getText().contains(ProductName));
		Assert.assertTrue(match);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Checkout')]")));
		
		Actions a= new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"))).click().perform();
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),Country).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ta-results")));
		
		List<WebElement> options = driver.findElements(By.xpath("//button[contains(@class,'ta-item')] [contains(.,'France')]"));
		WebElement targetCountry = options.stream().filter(c -> c.getText().trim().toLowerCase()
		.contains(Country.trim().toLowerCase())).findFirst().orElseThrow(() -> new RuntimeException("Country not found"));
		a.click(targetCountry).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().perform();

		WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String actualMessage = confirmationMessage.getText().trim();
		Assert.assertTrue(actualMessage.equalsIgnoreCase("Thankyou for the order."));
		
		WebElement orderIdElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".content-wrap")));
		String rawText = orderIdElement.getText();
		String orderIdLine = java.util.Arrays.stream(rawText.split("\n"))
			    .filter(line -> line.contains("|"))
			    .findFirst()
			    .orElseThrow(() -> new RuntimeException("Order ID line not found"));
		String OrderID= orderIdLine.replace("|"," ").trim();
		System.out.println("Order ID is : "+ OrderID);
		driver.quit();
			}

}
