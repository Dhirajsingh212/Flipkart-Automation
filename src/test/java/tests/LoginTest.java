package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testing.base.BaseClass;
import com.testing.pages.LoginPage;
import com.testing.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class LoginTest extends BaseClass {
    LoginPage lp;
    ExtentReports extent;
    ExtentTest test;

    @Parameters({"browser"})
    @BeforeSuite
    public void CreateBrowserInstance(String browser) throws IOException {
        OpenBrowser(browser);
    }

    @BeforeTest
    public void InitializeObjects() {
        lp = new LoginPage(driver);
        extent = ExtentManager.getInstance();
    }

    @Test(dependsOnMethods = "VerifyLoginUIElements")
    public void LoginWithInvalidPhoneNumber() {
        test = extent.createTest("TC:1 Login with invalid phone number");
        test.log(Status.INFO, "Login with invalid phone number Started");

        lp.SetPhoneNumber("111111111111");
        lp.SubmitButton();
        String expected = "Please enter valid Email ID/Mobile number";
        Assert.assertEquals(lp.GetErrorMessage(), expected);

        test.log(Status.PASS, "Login with invalid phone number Executed successfully");
    }

    @Test(dependsOnMethods = "VerifyLoginUIElements")
    public void LoginWithValidPhoneNumber() {
        test = extent.createTest("TC:2 Login with valid phone number");
        test.log(Status.INFO, "Login with valid phone number Started");

        lp.SetPhoneNumber("8420221214");
        lp.SubmitButton();
        Assert.assertTrue(lp.VerifyOTPButtonIsPresent());

        test.log(Status.PASS, "Login with valid phone number Executed successfully");
    }

    @Test
    void VerifyLoginUIElements() {
        test = extent.createTest("TC:3 Verification of Login page UI elements");
        test.log(Status.INFO, "Verify Login page UI elements Started");

        lp.RedirectToLoginPage();
        Assert.assertTrue(lp.PhoneNumberInputIsPresent());
        Assert.assertTrue(lp.RequestOTPButtonIsPresent());

        test.log(Status.PASS, "Verify Login page UI elements Finished");
    }

    @AfterSuite()
    public void CloseBrowserInstance() {
        CloseBrowser();
        extent.flush();
    }

}
