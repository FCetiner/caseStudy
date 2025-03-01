package utils.driverClient;

import io.cucumber.java.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.util.ConfigReader;
import utils.propertyManager.PropertyManager;
import utils.util.Configs;

import java.io.IOException;
import java.time.Duration;

public class DriverClient extends PropertyManager {
    private static WebDriver driver;

    /**
     * Initializes the ChromeDriver without specifying a path.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver(){
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    // (Opsiyonel) Başlatma parametreleri eklenebilir:
                    // options.addArguments("--headless");
                    // options.addArguments("--disable-gpu");
                    // options.addArguments("--incognito");

                    driver = new ChromeDriver(options);
                //    driver.get(Configs.getAllConfigs().get("ENVIRONMENT_DOMAIN"));
                    break;

                case "edge":
                    System.out.println("edge");
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    break;

                case "firefox":
                    driver = new FirefoxDriver(new FirefoxOptions());
                    driver.manage().window().maximize();
                    break;

                case "chrome-headless":
                    options = new ChromeOptions();
                    options.addArguments("--headless","--start-maximized","--disable-gpu","--ignore-certificate-errors","--disable-extensions","--no-sandox","--disable-dev-shm-usage"); // reklamları kapatır --disable-dev-shm-usage (--force-device-scale-factor=0.8)
                    driver = new ChromeDriver(options);
                    break;

                case "edge-headless":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    // Set the headless mode.
                    driver = new EdgeDriver(edgeOptions);
                    break;

                case "safari":
                    // Set the headless mode.
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    break;

                default:
                    driver = new ChromeDriver(new ChromeOptions());
                    break;
            }
            driver.get(Configs.getAllConfigs().get("ENVIRONMENT_DOMAIN"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        return driver;
    }

    /**
     * Quits the WebDriver instance and cleans up resources.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    public static void wait(int sec) {
        try {
            Thread.sleep(1000L * sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void closeDriver() {
        if (driver !=null){
            driver.close();
            driver =null;
        }
    }


    public static void waitAndClick(WebElement element) {
        int timeout = 5;
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }


    public static void waitUntilVisibilityOfElement(WebElement element) {
        WebDriverWait wait=new WebDriverWait(DriverClient.getDriver(),Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public static void waitForClickability(WebElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }


    public static void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public static void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }



    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }


    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

}
