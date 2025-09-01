package com.testing.base;

import com.testing.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;
import java.time.Duration;

public class BaseClass {
    public static WebDriver driver;
    public ConfigReader reader;

    public void OpenBrowser(String browser) throws IOException {
        WebDriverManager.chromedriver().setup();
        if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        reader = new ConfigReader();
        String baseUrl = reader.getProperty("url");
        driver.get(baseUrl);
    }

    public void CloseBrowser() {
        driver.quit();
    }
}