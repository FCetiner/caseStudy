package utils.driver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.config.Endpoints;
import utils.config.PropertyManager;

import java.time.Duration;

public class DriverClient {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverClient() {
        // Singleton pattern için private constructor
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = PropertyManager.getInstance().getProperty("browser");

            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver(getChromeOptions(false));
                    break;

                case "chrome-headless":
                    driver = new ChromeDriver(getChromeOptions(true));
                    break;

                case "firefox":
                    driver = new FirefoxDriver(new FirefoxOptions());
                    break;

                case "edge":
                    driver = new EdgeDriver(new EdgeOptions());
                    break;

                case "edge-headless":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    driver = new EdgeDriver(edgeOptions);
                    break;

                case "safari":
                    driver = new SafariDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driver.get(Endpoints.getBaseUrl());

            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait objesi
        }

        return driver;
    }

    private static ChromeOptions getChromeOptions(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        if (isHeadless) {
            options.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors",
                    "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage");
        }
        return options;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static void sendKeys(WebElement element, String text) {
        waitForElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public static void waitAndClick(WebElement element) {
        waitForClickability(element);
        try {
            element.click();
        } catch (WebDriverException e) {
            System.out.println("Click failed, retrying...");
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
