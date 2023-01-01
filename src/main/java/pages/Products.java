package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Products {
    WebDriver driver;
    WebDriverWait webWait;
    SoftAssert softAssert=new SoftAssert();
    By productsPageTittle= By.xpath("//android.widget.TextView[@text='PRODUCTS']");
    By product1AddtoCartBtn=By.xpath("(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]");
    By cartIcon=By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.view.ViewGroup");
    By removeBtn=By.xpath("//android.view.ViewGroup[@content-desc='test-REMOVE']/android.widget.TextView");
    public Products(WebDriver driver){
        this.driver=driver;
        webWait =new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    /**
     * This method is to get product page's tittle
     */
    @Step("Get Products Page's tittle")
    public String getPageTittleinProductsPage(){
        webWait.until(ExpectedConditions.visibilityOfElementLocated(productsPageTittle));
        return driver.findElement(productsPageTittle).getText();
    }
    /** This method is to add products to the cart
    * */
    @Step("Add product to the cart")
    public Products addProducttoCart(){
        webWait.until(ExpectedConditions.elementToBeClickable(product1AddtoCartBtn));
        driver.findElement(product1AddtoCartBtn).click();
        return this;
    }
    /**
     This method is to check that item is added and button add to cart is now Remove button
     */
    @Step("Check that item is added and button add to cart is now Remove button")
    public Products validateAddToCartBtn(String btnText){
        webWait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn));
        String removeBtnText = driver.findElement(removeBtn).getText();
        softAssert.assertEquals(removeBtnText,btnText);
        softAssert.assertAll();
        return this;
    }
    /**
     * This method is to take us to the cart page
     */

    @Step("Open Cart")
    public Cart openCart(){
        webWait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        driver.findElement(cartIcon).click();
        return new Cart(driver);
    }

}
