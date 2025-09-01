package com.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartAndOrderPage {
    public WebDriver driver;
    @FindBy(xpath = "(//div[@class='cPHDOP col-12-12']/div/div/div/a)[1]")
    WebElement FirstProduct;
    @FindBy(xpath = "(//button[@class='LcLcvv'])[2]")
    WebElement IncrementProductButton;
    @FindBy(xpath = "(//button[@class='LcLcvv'])[1]")
    WebElement DecrementProductButton;
    @FindBy(xpath = "//div[@class='sBxzFz' and text()='Remove']")
    WebElement RemoveButton;

    public CartAndOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int priceUtil(String price) {
        String modified = price.substring(1, price.length());
        String first = modified.split(",")[0];
        String second = modified.split(",")[1];
        return Integer.parseInt(first + second);
    }

    public void AddProductToCartFromDetailPage() {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(20)).ignoring(Exception.class);
        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" (//button[@class='QqFHMw vslbG+ In9uk2 JTo6b7' or @class='QqFHMw vslbG+ In9uk2'])[1]")));

        addToCartButton.click();
    }

    public void IncrementProductQuantity() {
        IncrementProductButton.click();
    }

    public void DecrementProductQuantity() {
        DecrementProductButton.click();
    }

    public int VerifyPrice() {
        String price = driver.findElement(By.xpath("(//div[@class='_1Y9Lgu'])[2]/span")).getText();
        return priceUtil(price);
    }

    public void RemoveProductFromCart() {
        RemoveButton.click();
        WebElement confirmRemoveButton = driver.findElement(By.xpath("//div[@class='sBxzFz fF30ZI A0MXnh']"));
        confirmRemoveButton.click();
    }

    public String GetAfterRemovalText() {
        return driver.findElement(By.xpath("//div[@class='s2gOFd']")).getText();
    }
}