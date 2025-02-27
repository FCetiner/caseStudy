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
    protected static WebDriver driver;
    static long TIMEOUT = 30;
    public static By SplashScreen = By.xpath("//img[@alt='Sendeo Logo']");
    public static By searchBoxInput = By.xpath("//input[@placeholder='Ara']");
    public static By dateButtonStartDate = By.xpath("(//input[@name='StartDate'])[1]/following-sibling::div/button");
    public static By previousMonthButton = By.xpath("(//div[@class='MuiPickersCalendarHeader-switchHeader']/button)[1]");
    public static By dateButtonEndDate = By.xpath("(//input[@name='StartDate'])[2]/following-sibling::div/button");
    public static By dateFromCalendarFirst = By.xpath("(//div[@class='MuiPickersCalendar-week']//p[text()='1'])[1]");
    public static By closeDropdown = By.xpath("(//button[@class='MuiButtonBase-root MuiIconButton-root'])[1]");
    public static By drawerButton = By.xpath("//button[@id='drawerBtn']");
    public static By monthDetailOnCalendar = By.xpath("//div[@class='MuiPickersCalendarHeader-switchHeader']/div/p");
    static String buttonFromDrawer = "(//a[contains(@href, '%s')])[1]";
    private final By billTypeDropdown = By.xpath("//div[@id='mui-component-select-InvoiceTypeId']");
    public static ArrayList<String> months = new ArrayList<>(Arrays.asList("Ocak 2024", "Şubat 2024", "Mart 2024", "Nisan 2024", "Mayıs 2024", "Haziran 2024", "Temmuz 2024", "Ağustos 2024", "Eylül 2024", "Ekim 2024", "Kasım 2024", "Aralık 2024"));

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }
    public BasePage(){
        PageFactory.initElements(DriverClient.getDriver(), this);}

    /**
     * Finds an element with an explicit wait until it is visible.
     *
     * @param locator the locator of the element
     * @return the visible WebElement
     */
    public static WebElement
    findElementWithWait(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Finds multiple elements with an explicit wait until all are visible.
     *
     * @param locator the locator of the elements
     * @return a list of visible WebElements
     */
    public List<WebElement> findElementsWithWait(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
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
        return findElementWithWait(locator).isDisplayed();
    }

    /**
     * Enters text into a field.
     *
     * @param locator the locator of the field
     * @param text    the text to enter
     */
    public static void enterText(By locator, String text) {
        findElementWithWait(locator).sendKeys(text);
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

    public static void waitForSplashScreen() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SplashScreen));
    }

    /**
     * Clicks an element.
     *
     * @param locator the locator of the element
     */
    public static void clickElement(By locator) {
        System.out.println("locator = " + locator);findElementWithWait(locator).click();
    }

    public static void hoverOnElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver); // Actions sınıfını başlat
        actions.moveToElement(element).perform(); // Hover işlemini gerçekleştir
    }

    public void waitAndClick(WebElement element) throws InterruptedException {
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

    public void waitUntilVisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public static void clickWithJS(By locator) throws IOException {
        WebElement webElement = findElementWithWait(locator);
        ((JavascriptExecutor) DriverClient.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
        ((JavascriptExecutor) DriverClient.getDriver()).executeScript("arguments[0].click();", webElement);
    }

    public static void scrollToWebElementVisible(By element) throws IOException {
        WebElement webElement = findElementWithWait(element);
        JavascriptExecutor jss = (JavascriptExecutor) DriverClient.getDriver();
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
     * Clears a field.
     *
     */
    public static void dateSetter(String givenMonth, String startOrEndDate) throws IOException {

        if (startOrEndDate.equals("Start")) {
            clickElement(dateButtonStartDate);
        } else {
            clickElement(dateButtonEndDate);
        }

        for (int i = 0; i < months.size(); i++) {
            if (getText(monthDetailOnCalendar).equals(givenMonth)) {
                clickElement(dateFromCalendarFirst);
                break;
            } else {
                clickElement(previousMonthButton);
            }
        }
    }

    public static void waitForElementToBeInvisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void navigateToDrawer(String pageName, String pageHref) throws InterruptedException {
        clickElement(drawerButton);
        enterText(searchBoxInput, pageName);
        String formattedPageName = String.format(buttonFromDrawer, pageHref);
        clickElement(By.xpath(formattedPageName));
        BasePage.waitForSplashScreen();
        clickElement(closeDropdown);
        Thread.sleep(3000);
    }
}
