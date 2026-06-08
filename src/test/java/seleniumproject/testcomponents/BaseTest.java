package seleniumproject.testcomponents;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	    
		String remoteUrl = System.getProperty("selenium.remote.url");
	    //WebDriverManager.chromedriver().setup();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("--headless=new");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--window-size=1920,1080");
	    
	    if (remoteUrl != null) {
	        try {
	            // Point to the dedicated Docker  container using the modern URI conversion method
	            driver = new RemoteWebDriver(URI.create(remoteUrl).toURL(), options);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }else {
	        // Fallback for when you run locally inside Eclipse UI
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver(options);
	    }
	    //driver = new ChromeDriver(options); 
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    //driver.manage().window().maximize();
	    
	    return driver;
	}
}