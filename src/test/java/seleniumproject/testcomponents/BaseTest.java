package seleniumproject.testcomponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    public WebDriver driver;

    @BeforeMethod
    public void launchApplication() {
        driver = initializeDriver();
        driver.get("https://rahulshettyacademy.com/client");
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

	public WebDriver initializeDriver() { 
	    
	    WebDriverManager.chromedriver().setup();
	    ChromeOptions options = new ChromeOptions();
	    
	    driver = new ChromeDriver(options); 
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	    
	    return driver;
	}
}