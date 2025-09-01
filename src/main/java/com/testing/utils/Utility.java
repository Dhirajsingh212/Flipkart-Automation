package com.testing.utils;

import org.openqa.selenium.WebDriver;

public class Utility {
    public static String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public static void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }
}
