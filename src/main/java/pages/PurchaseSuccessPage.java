package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class PurchaseSuccessPage {
    WebDriver driver;
    WebDriverWait webWait;
    SoftAssert softAssert=new SoftAssert();
    By purchasedSuccessMsg=By.xpath("//android.widget.ScrollView[@content-desc='test-CHECKOUT: COMPLETE!']/android.view.ViewGroup/android.widget.TextView[1]");
By backHomeBtn=new AppiumBy.ByAccessibilityId("test-BACK HOME");
    public PurchaseSuccessPage(WebDriver driver) {
        this.driver = driver;
        webWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Validate success purchase")
    public PurchaseSuccessPage validateSucccessPurchase(String msg){
        webWait.until(ExpectedConditions.elementToBeClickable(backHomeBtn));
        softAssert.assertEquals(driver.findElement(purchasedSuccessMsg).getText(),msg);
        softAssert.assertAll();
        return this;
    }
}
