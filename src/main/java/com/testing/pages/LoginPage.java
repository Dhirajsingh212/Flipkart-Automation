package com.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;
    @FindBy(xpath = "(//a[@title='Login'])[1]")
    WebElement loginRedirectButton;
    @FindBy(xpath = "//input[contains(@class,'BV+Dqf')]")
    WebElement phoneNumberInput;
    @FindBy(xpath = "//button[@class='QqFHMw twnTnD _7Pd1Fp' and text()='Request OTP']")
    WebElement requestOTPButton;
    @FindBy(xpath = "//span[@class='llBOFA']/span")
    WebElement errorMessage;
    @FindBy(xpath = "//button[@class='QqFHMw llMuju M5XAsp']")
    WebElement verifyOTPButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void RedirectToLoginPage() {
        loginRedirectButton.click();
    }

    public void SetPhoneNumber(String phoneno) {
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(phoneno);
    }

    public void SubmitButton() {
        requestOTPButton.click();
    }

    public String GetErrorMessage() {
        return errorMessage.getText();
    }

    public Boolean VerifyOTPButtonIsPresent() {
        return verifyOTPButton.isDisplayed();
    }

    public Boolean PhoneNumberInputIsPresent() {
        return phoneNumberInput.isDisplayed();
    }

    public Boolean RequestOTPButtonIsPresent() {
        return requestOTPButton.isDisplayed();
    }
}