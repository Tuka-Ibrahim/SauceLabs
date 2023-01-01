package pages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class Cart {
    WebDriver driver;
    WebDriverWait webWait;
    SoftAssert softAssert=new SoftAssert();
    By productTittle= By.xpath("//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]");
    By productPrice= By.xpath("//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView[1]");
    By removeItemBtn=new AppiumBy.ByAccessibilityId("test-REMOVE");
    By numberOfItemsInCart=By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.TextView");
    By checkoutBtn=new AppiumBy.ByAccessibilityId("test-CHECKOUT");

    public Cart(WebDriver driver) {
        this.driver = driver;
        webWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
/** This method is to validate that Title and price of the Item at PRODUCTS page equals the item and price at the cart
@param productTittleInput is the chosen product 's tittle in the PRODUCTS PAGE
 @param productSubtotal is the chosen product 's price in the PRODUCTS PAGE
 */
    @Step("Validate the right product is added to the cart")
    public Cart validateRightProductisAdded(String productTittleInput,String productSubtotal){
        webWait.until(ExpectedConditions.visibilityOfElementLocated(productTittle));
        webWait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        softAssert.assertEquals(driver.findElement(productTittle).getText(),productTittleInput);
        softAssert.assertEquals(driver.findElement(productPrice).getText(),productSubtotal);
        softAssert.assertAll();
        return this;
    }
    /**
     This method is to click on the remove button in the cart page
     */
    @Step("Click on Remove button")
    public Cart ClickonRemoveBtn(){
        webWait.until(ExpectedConditions.elementToBeClickable(removeItemBtn));
        driver.findElement(removeItemBtn).click();
        return this;
    }
    /**
     This method used to validate the removing process of the product, if the item is removed then the remove button will
     be no longer visible
     */
    @Step("Validate removing items in the cart")
    public Cart validateRemovingProduct(){
        //We can find a bug here that Website don't show a feedback to the user when removing an item.
        softAssert.assertTrue(webWait.until(ExpectedConditions.invisibilityOfElementLocated(removeItemBtn)),"Item removed");
        softAssert.assertAll();
        return this;
    }
    /**
     This method used to validate if the cart is empty, if there's no longer items in the cart then the counter of items exists on the cart icon
     be no longer visible
     */

    @Step("Validate that the cart is empty")
    public Cart validateCartIsEmpty(){
        softAssert.assertTrue(webWait.until(ExpectedConditions.invisibilityOfElementLocated(numberOfItemsInCart)),"Cart is Empty");
        softAssert.assertAll();
        return this;
    }
    /**
     This method is to take us to the checkout page
    */
    @Step("Go to Checkout")
    public Checkout clickOnCheckout(){
        webWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        driver.findElement(checkoutBtn).click();
        return new Checkout(driver);
    }

}