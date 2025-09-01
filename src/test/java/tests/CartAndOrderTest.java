package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testing.base.BaseClass;
import com.testing.pages.CartAndOrderPage;
import com.testing.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CartAndOrderTest extends BaseClass {
    CartAndOrderPage cartAndOrderPage;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void IntializeObjects() {
        cartAndOrderPage = new CartAndOrderPage(driver);
        extent = ExtentManager.getInstance();
    }

    @Test
    public void AddProductToCart() {
        test = extent.createTest("TC:9 Add Product To Cart");
        test.log(Status.INFO,"Add Product To Cart Started");

        cartAndOrderPage.AddProductToCartFromDetailPage();

        test.log(Status.PASS,"Add Product To Cart Executed");
    }

    @Test(dependsOnMethods = {"AddProductToCart"})
    public void VerifyProductPriceByChangingQTY() throws InterruptedException {
        test = extent.createTest("TC:10 Verify Product Price By Changing QTY");
        test.log(Status.INFO,"Verify Product Price By Changing QTY Started");

        int priceBefore = cartAndOrderPage.VerifyPrice();
        Thread.sleep(5000);
        cartAndOrderPage.IncrementProductQuantity();
        Thread.sleep(5000);
        test.log(Status.INFO,"Quantity changed successfully");

        int priceAfter = cartAndOrderPage.VerifyPrice();
        Assert.assertEquals(priceAfter, priceBefore * 2);

        test.log(Status.PASS,"Verify Product Price By Changing QTY Passed");
    }

    @Test(dependsOnMethods = {"VerifyProductPriceByChangingQTY"})
    public void RemoveProductFromCart() {
        test = extent.createTest("TC:11 Remove Product From Cart");
        test.log(Status.INFO,"Remove Product From Cart Started");

        cartAndOrderPage.RemoveProductFromCart();
        String afterRemovalText = cartAndOrderPage.GetAfterRemovalText();
        String expectedText = "Missing Cart items?";
        Assert.assertEquals(afterRemovalText, expectedText);

        test.log(Status.PASS,"Remove Product From Cart Executed");
    }
}
