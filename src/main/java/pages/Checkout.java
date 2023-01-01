package pages;

import Actions.MobileActions;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Checkout {
    WebDriver driver;
    WebDriverWait webWait;
    SoftAssert softAssert=new SoftAssert();
    By productTittle= By.xpath("//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]");
    By productPrice= By.xpath("//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView[1]");
    By firstName=new AppiumBy.ByAccessibilityId("test-First Name");
    By lastName=new AppiumBy.ByAccessibilityId("test-Last Name");
    By postalCode=new AppiumBy.ByAccessibilityId("test-Zip/Postal Code");
    By totalPrice=By.xpath("//android.widget.ScrollView[@content-desc='test-CHECKOUT: OVERVIEW']/android.view.ViewGroup/android.widget.TextView[7]");
    By continueBtn=new AppiumBy.ByAccessibilityId("test-CONTINUE");
    By finishBtn=new AppiumBy.ByAccessibilityId("test-FINISH");
    public Checkout(WebDriver driver) {
        this.driver = driver;
        webWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Fill your ceredntials to continue to checkout")
    public Checkout fillCredntialsinCheckout(String firstNameInput,String lastNameInput,String postalCodeInput){
        webWait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).sendKeys(firstNameInput);
        driver.findElement(lastName).sendKeys(lastNameInput);
        driver.findElement(postalCode).sendKeys(postalCodeInput);
        return this;
    }
    @Step("Scroll down to Finish btn")
    public Checkout clickOnContinue(){
        driver.findElement(continueBtn).click();
        return this;
    }
    @Step("Validate tittle,subtotal and total of the price")
    public Checkout validateProductInformationinCheckout(String productTittleInput,String productSubtotal,String productTotal){
        webWait.until(ExpectedConditions.visibilityOfElementLocated(productTittle));
        softAssert.assertEquals(driver.findElement(productTittle).getText(),productTittleInput);
        softAssert.assertEquals(driver.findElement(productPrice).getText(),productSubtotal);
        softAssert.assertEquals(driver.findElement(totalPrice).getText(),productTotal);
        softAssert.assertAll();
        return this;
    }
    @Step("Scroll down to Finish btn")
    public Checkout scrollDown(String text){
        MobileActions.scrollDownToSpecificText(text);
        return this;
    }
    @Step("Click on Finish btn")
    public PurchaseSuccessPage clickOnFinishButton(){
        webWait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        driver.findElement(finishBtn).click();
        return new PurchaseSuccessPage(driver);
    }

}
