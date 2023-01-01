package TestPackage;

import Actions.JsonFileManager;
import Actions.MobileActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart;
import pages.Login;
import pages.Products;

import java.net.MalformedURLException;
import java.net.URL;

public class CheckoutTest {
    WebDriver driver;
    JsonFileManager testData=new JsonFileManager("src/test/resources/testDataFiles/simpleJSON.json");
    Login login;
    Products product;
    Cart cart;
    @BeforeMethod(description = "Set Capabilities for the test device and login user by a valid username and password.")
    public void setupDevice() throws MalformedURLException {
        String AppName = System.getProperty("user.dir") + "/src/test/resources/testDataFiles/Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Demo");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        caps.setCapability("appium:platformVersion", "11");
        caps.setCapability("appium:automationName", "UiAutomator2");
        driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
        login=new Login(driver);
        login.fillLoginForm(testData.getTestData("usernameValid"),testData.getTestData("passwordValid"));
        product=new Products(driver);
        product.addProducttoCart();
        cart=new Cart(driver);
        new MobileActions(driver);
    }
    @Test(description = "Validate the complete cycle to purchase a product")
    public void validateOnlineOrderingFlow(){
        product.openCart()
                .clickOnCheckout()
                .fillCredntialsinCheckout(
                        testData.getTestData("credentials.firstNameInput"),
                        testData.getTestData("credentials.lastNameInput"),
                        testData.getTestData("credentials.postalCodeInput"))
                .clickOnContinue()
                .validateProductInformationinCheckout(
                        testData.getTestData("item1Info.sauce-labs-backpack-tittle"),
                        testData.getTestData("item1Info.sauce-labs-backpack-price"),
                        testData.getTestData("item1Info.totalPrice")
                )
                .scrollDown(testData.getTestData("scrollDownText"))
                .clickOnFinishButton()
                .validateSucccessPurchase(testData.getTestData("responsemsgs.purchasedSuccess"));
    }
    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        driver.quit();
    }


}
