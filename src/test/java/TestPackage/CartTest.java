package TestPackage;

import Actions.JsonFileManager;
import Actions.MobileActions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Login;
import pages.Products;

import java.net.MalformedURLException;
import java.net.URL;

public class CartTest {
    WebDriver driver;
    JsonFileManager testData=new JsonFileManager("src/test/resources/testDataFiles/simpleJSON.json");
    Login login;
    Products product;
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
        new MobileActions(driver);
    }
    @Test(description = "Validate that tittle and price of the product added to the cart is the same as the product selected in the home page")
    public void validateAdditionofTheProductoCartProcess(){
        product.addProducttoCart()
                .validateAddToCartBtn(testData.getTestData("removebtn"))
                .openCart()
                .validateRightProductisAdded(
                        testData.getTestData("item1Info.sauce-labs-backpack-tittle"),
                        testData.getTestData("item1Info.sauce-labs-backpack-price")
                );
    }
    @Test(description = "Validate removing products in the cart")
    public void validateRemovingProducts(){
        product.addProducttoCart()
                .openCart()
                .ClickonRemoveBtn()
                .validateRemovingProduct()
                .validateCartIsEmpty();
    }
    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        driver.quit();
    }

}
