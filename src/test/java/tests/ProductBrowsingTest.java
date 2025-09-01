package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testing.base.BaseClass;
import com.testing.pages.ProductBrowsingPage;
import com.testing.utils.ExtentManager;
import com.testing.utils.Utility;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProductBrowsingTest extends BaseClass {
    ProductBrowsingPage productBrowsingPage;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void InitializeObjects() {
        productBrowsingPage = new ProductBrowsingPage(driver);
        extent = ExtentManager.getInstance();
    }

    @Test(priority = -1)
    public void SearchWithoutAnyText() {
        test = extent.createTest("TC:4 Search without any text");
        test.log(Status.INFO,"Search without any text Started");

        productBrowsingPage.NavigateToHomePage();
        productBrowsingPage.CloseLoginPopup();
        productBrowsingPage.SearchText("");
        String expectedTitle = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
        String pageTitle = Utility.getTitle(driver);
        Assert.assertEquals(pageTitle, expectedTitle);

        test.log(Status.PASS,"Search without any text Finished");
    }

    @Test
    public void SearchProduct() {
        test = extent.createTest("TC:5 Search product");
        test.log(Status.INFO,"Search product Started");

        String productName = "mobile phone";
        test.log(Status.INFO,"Search product Name: " + productName);

        productBrowsingPage.SearchText(productName);
        Assert.assertEquals(productBrowsingPage.GetSearchedProductName(), productName);

        test.log(Status.PASS,"Search product Finished");
    }

    @Test(dependsOnMethods = {"SearchProduct"})
    public void ApplyFilter() throws InterruptedException {
        test = extent.createTest("TC:6 Apply Filter");
        test.log(Status.INFO,"Apply Filter Started");

        productBrowsingPage.SetRamFilter();
        test.log(Status.INFO,"Apply Filter Ram Filter Executed");
        Thread.sleep(5000);

        productBrowsingPage.SetBrandFilter();
        test.log(Status.INFO,"Apply Filter Brand Filter Executed");
        Thread.sleep(5000);

        productBrowsingPage.SetMinRange();
        test.log(Status.INFO,"Apply Filter Min Range Executed");
        Thread.sleep(5000);

        productBrowsingPage.SetMaxRange();
        test.log(Status.INFO,"Apply Filter Max Range Executed");
        Thread.sleep(5000);

        test.log(Status.PASS,"Apply Filter Finished");
    }

    @Test(dependsOnMethods = {"ApplyFilter"})
    public void SortProduct() {
        test = extent.createTest("TC:7 Sort Product");
        test.log(Status.INFO,"Sort Product Started");

        productBrowsingPage.SetSortFilter();

        test.log(Status.PASS,"Sort Product Executed");
    }

    @Test(dependsOnMethods = {"SortProduct"})
    public void VerifyProductDetails() throws InterruptedException {
        test = extent.createTest("TC:8 Verify Product Details");
        test.log(Status.INFO,"Verify Product Details Started");

        productBrowsingPage.SelectFirstProduct();
        Boolean evaluatedValue = productBrowsingPage.CheckSpecification();
        Assert.assertTrue(evaluatedValue);

        test.log(Status.PASS,"Verify Product Details Finished");
    }
}
