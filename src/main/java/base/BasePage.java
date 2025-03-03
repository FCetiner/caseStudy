package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driverClient.DriverClient;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasePage {
    protected static final long TIMEOUT = 30;
    public BasePage(){
        PageFactory.initElements(DriverClient.getDriver(), this);}

    protected static WebDriver getDriver() {
        return DriverClient.getDriver();
    }

    /**
     * Finds an element with an explicit wait until it is visible.
     *
     * @param locator the locator of the element
     * @return the visible WebElement
     */
    public static WebElement findElementWithWait(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Finds multiple elements with an explicit wait until all are visible.
     *
     * @param locator the locator of the elements
     * @return a list of visible WebElements
     */
    public static List<WebElement> findElementsWithWait(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Retrieves the text of an element.
     *
     * @param locator the locator of the element
     * @return the text of the element
     */
    public static String getText(By locator) {
        return findElementWithWait(locator).getText();
    }

    /**
     * Checks if an element is visible on the screen.
     *
     * @param locator the locator of the element
     * @return true if visible, otherwise false
     */
    public static boolean isElementVisible(By locator) {
        try {
            return findElementWithWait(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Enters text into a field.
     *
     * @param locator the locator of the field
     * @param text    the text to enter
     */
    public static void enterText(By locator, String text) {
        WebElement element = findElementWithWait(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Enters text into a field.
     *
     * @param locator the locator of the field
     * @param text    the text to enter
     */
    public static void enterDate(By locator, String text) {
        findElementWithWait(locator).sendKeys(text);
        findElementWithWait(locator).sendKeys(Keys.RETURN);
    }

    /**
     * Clears a field.
     *
     * @param locator the locator of the field
     */
    public void clear(By locator) {
        findElementWithWait(locator).clear();
    }

    /**
     * Clicks an element.
     *
     * @param locator the locator of the element
     */
    public static void clickElement(By locator) {
        findElementWithWait(locator).click();
    }

    /**
     * Hovers over an element
     */
    public static void hoverOnElement(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * Waits for element visibility
     */
    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element to be clickable
     */
    public static void waitForClickability(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Clicks element using JavaScript
     */
    public static void clickWithJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollToWebElementVisible(By element) throws IOException {
        WebElement webElement = findElementWithWait(element);
        JavascriptExecutor jss = (JavascriptExecutor) getDriver();
        jss.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void scrollHorizontallyToElement(WebDriver driver, WebElement scrollableArea, WebElement targetElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int targetOffset = targetElement.getLocation().getX();
        int containerOffset = scrollableArea.getLocation().getX();
        int scrollAmount = targetOffset - containerOffset;
        js.executeScript("arguments[0].scrollLeft = arguments[1];", scrollableArea, scrollAmount);
    }

    /**
     * Waits and clicks with retry
     */
    public static void waitAndClick(WebElement element) {
        int timeout = 5;
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void waitUntilVisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }



    public static void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
