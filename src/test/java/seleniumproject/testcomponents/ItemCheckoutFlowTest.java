package seleniumproject.testcomponents;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import seleniumproject.pageobject.CartVerification;
import seleniumproject.pageobject.LandingPage;
import seleniumproject.pageobject.OrderConfirmationPage;
import seleniumproject.pageobject.ProductCatalog;
import seleniumproject.pageobject.ShippingInfo;

public class ItemCheckoutFlowTest extends BaseTest {

    @Test(dataProvider = "getData")
    public void submitOrderFlow(String email, String password, String ProductName, String Country) throws Exception {
        
        //String ProductName = "ZARA COAT 3";
        //String Country = "France";
    	//String email ="sagatest@gmail.com";
    	//String password = "Password@1";
		
		
		//Login page
		LandingPage landingpage =new LandingPage(driver);
		//landingpage.goTo();
		landingpage.loginApplication(email,password);


		//ProductHome page
		ProductCatalog productcatalog = new ProductCatalog(driver);
		List<WebElement> products=productcatalog.getProductList();
		productcatalog.addProducttoCart(ProductName);
		productcatalog.clickCart();
		
		//Cart page
		CartVerification cartverification=new CartVerification(driver);
		cartverification.cartCheck(ProductName);
		cartverification.checkOut(Country);
		
		//Checkout page
		ShippingInfo shippinginfo=new ShippingInfo(driver);
		shippinginfo.countrydropdown(Country);
		shippinginfo.submitButton();
		
        //OrderConfirmation Page
		OrderConfirmationPage orderconfirmationpage=new OrderConfirmationPage(driver);
		orderconfirmationpage.thankyouMessage();
		orderconfirmationpage.extractOrderID();
		
			}
    @DataProvider
    public Object[][] getData(){
    	return new Object[][]{
    			{ "sagatest@gmail.com", "Password@1", "ZARA COAT 3", "France" },
                { "Rai1997@gmail.com", "Tester@12345", "ADIDAS ORIGINAL", "India" }
    	};
    }
    

}
