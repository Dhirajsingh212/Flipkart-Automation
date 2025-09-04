package com.testing.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class ProductBrowsingPage {
    public WebDriver driver;
    JavascriptExecutor jse;

    @FindBy(xpath = "(//div[@class='F9+fd2']/a)[1]")
    WebElement homeButton;

    @FindBy(xpath = "//input[@class='Pke_EE']")
    WebElement searchBoxInput;

    @FindBy(xpath = "//span[@class='BUOuZu']/span")
    WebElement searchConfirmTextElement;

    //7-12
    @FindBy(xpath = "(//div[@class='ewzVkT _3DvUAf'])[7]/div/label")
    WebElement ramFilter;

    //1-6
    @FindBy(xpath = "(//div[@class='ewzVkT _3DvUAf'])[4]/div/label")
    WebElement brandFilter;

    @FindBy(xpath = "(//select[@class='Gn+jFg'])[1]")
    WebElement MinRangeSelect;

    @FindBy(xpath = "(//select[@class='Gn+jFg'])[2]")
    WebElement MaxRangeSelect;

    @FindBy(xpath = "//div[contains(@class,'zg-M3Z') and text()='Popularity']")
    WebElement SortByPopularity;

    @FindBy(xpath = "(//div[@class='cPHDOP col-12-12']/div/div/div/a)[1]")
    WebElement FirstProduct;

    @FindBy(xpath = "(//div[@class='KzDlHZ'])[1]")
    WebElement FirstProductName;

    @FindBy(xpath = "//span[@class='_30XB9F' and @role='button']")
    WebElement closeLoginPopupButton;

    public ProductBrowsingPage(WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void SearchText(String text) {
        searchBoxInput.sendKeys(text);
        searchBoxInput.sendKeys(Keys.ENTER);
    }

    public void SetRamFilter() {
        jse.executeScript("arguments[0].scrollIntoView(true);" +
                "arguments[0].click();", ramFilter);
    }


    public void SetBrandFilter() {
        jse.executeScript("arguments[0].click();", brandFilter);
    }


    public void SetMinRange() {
        jse.executeScript("arguments[0].scrollIntoView(true)", MinRangeSelect);
        Select s = new Select(MinRangeSelect);
        s.selectByValue("10000");
    }

    public void SetMaxRange() {
        jse.executeScript("arguments[0].scrollIntoView(true)", MaxRangeSelect);
        Select s = new Select(MaxRangeSelect);
        s.selectByValue("20000");
    }

    public void SetSortFilter() {
        jse.executeScript("arguments[0].scrollIntoView(true);" +
                "arguments[0].click();", SortByPopularity);
    }

    public void SelectFirstProduct() {
        jse.executeScript("arguments[0].scrollIntoView(true);" +
                "arguments[0].click();", FirstProduct);
    }

    public Boolean CheckSpecification() {
        System.out.println("------------Entered CheckSpecification--------------");

        String main = driver.getWindowHandle();
        Set<String> list = driver.getWindowHandles();

        System.out.println("-------------GOT MAIN AND LIST--------------");

        String child = list.stream().filter(handle -> !handle.equalsIgnoreCase(main)).findFirst().get();
        driver.switchTo().window(child);

        System.out.println("switched to child");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='VU-ZEz']")));
        WebElement specification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_8tSq3v']")));

        System.out.println(product.isDisplayed() ? "Product is present" : "Product is not present");
        System.out.println(specification.isDisplayed() ? "Specification is present" : "Specification is not present");

        return product.isDisplayed() && specification.isDisplayed();
    }

    public void NavigateToHomePage() {
        homeButton.click();
    }

    public String GetSearchedProductName() {
        return searchConfirmTextElement.getText();
    }

    public void CloseLoginPopup() {
        closeLoginPopupButton.click();
    }

}