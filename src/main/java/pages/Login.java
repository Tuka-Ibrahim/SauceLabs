package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    WebDriver driver;
    WebDriverWait webWait;
    By username=new AppiumBy.ByAccessibilityId("test-Username");
    By password=new AppiumBy.ByAccessibilityId("test-Password");
    By loginBtn=new AppiumBy.ByAccessibilityId("test-LOGIN");
    By loginFailedError=By.xpath("//android.widget.TextView[@text='Username and password do not match any user in this service.']");
    public Login(WebDriver driver){
        this.driver=driver;
        webWait =new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public Products fillLoginForm(String usernameInput,String passwordInput){
        webWait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).sendKeys(usernameInput);
        driver.findElement(password).sendKeys(passwordInput);
        webWait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
        return new Products(driver);
    }
    public String getFailedLoginErrorMsg(){
        return driver.findElement(loginFailedError).getText();
    }



//    private SHAFT.GUI.WebDriver driver;
//    private String url = "https://www.google.com/";
//    private String title = "Google";
//    private By searchBox = By.name("q");
//
//    public Home(SHAFT.GUI.WebDriver driver){
//        this.driver = driver;
//    }
//
//    @Step("When I navigate to the Home page.")
//    public Home navigate(){
//        driver.browser().navigateToURL(url);
//        return this;
//    }
//
//    @Step("Then the browser title should be 'Google'.")
//    public Home verifyBrowserTitleIsCorrect(){
//        driver.verifyThat().browser().title().isEqualTo(title).perform();
//        return this;
//    }
//
}
