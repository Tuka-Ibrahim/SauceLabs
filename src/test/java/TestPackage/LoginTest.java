package TestPackage;

import Actions.JsonFileManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Login;
import pages.Products;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {
    WebDriver driver;
    SoftAssert softAssert=new SoftAssert();
    JsonFileManager testData=new JsonFileManager("src/test/resources/testDataFiles/simpleJSON.json");
    /**
     * This method is used before any testcase that will setup the device by setting its capabilities
     and initializing the mobile operating system
     * */
    @BeforeMethod(description = "Set Capabilities for the test device.")
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
    }
    /**
     * This method will validate when the user enters a valid username and password it will login successfully
     */

    @Test(description = "Validate when user enters a valid username or password.")
    public void LoginSuccessValidation(){
        new Login(driver).fillLoginForm(testData.getTestData("usernameValid"),testData.getTestData("passwordValid"));
        String productsTittle=new Products(driver).getPageTittleinProductsPage();
        softAssert.assertEquals(productsTittle,testData.getTestData("pageTittle"));
        softAssert.assertAll();
    }
    /**
     This method will validate when the user enters Invalid username or password it will not allow the user to login
     * */
    @Test(description = "Validate when user enters Invalid username or password.")
    public void LoginFailedValidation(){
        new Login(driver).fillLoginForm(testData.getTestData("usernameValid"),testData.getTestData("passwordInValid"));
        String errorMsg=new Login(driver).getFailedLoginErrorMsg();
        softAssert.assertEquals(errorMsg, testData.getTestData("responsemsgs.UsernameandPasswordInvalidError"));
        softAssert.assertAll();
    }
    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        driver.quit();
    }
}

