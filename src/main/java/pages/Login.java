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
    /**
     * This method is to fill the credntials needed to login
     * @param usernameInput is from the test data: username to login
     * @param passwordInput is from the test data: password to login
     */

    public Products fillLoginForm(String usernameInput,String passwordInput){
        webWait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).sendKeys(usernameInput);
        driver.findElement(password).sendKeys(passwordInput);
        webWait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
        return new Products(driver);
    }
    /**
     * This method is to return the error msg when login fails
     */

    public String getFailedLoginErrorMsg(){
        return driver.findElement(loginFailedError).getText();
    }
}
