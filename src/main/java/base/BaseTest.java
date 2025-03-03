package base;

import org.openqa.selenium.WebDriver;
import pages.*;
import utils.driverClient.DriverClient;

public class BaseTest {


    private static LoginPage loginPage;
    private static SignupPage signupPage;
    private static DashboardPage dashboardPage;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    /**
     * Initializes and returns the Web driver instance.
     * Ensures a single driver instance is created (Singleton pattern).
     */
    public static WebDriver getDriver()  {
        if (driverThreadLocal.get() == null) {
            // driver initialization
            driverThreadLocal.set(DriverClient.getDriver());
        }
        return driverThreadLocal.get();
    }

    /**
     * Quits the Web driver and resets all page instances.
     * Ensures proper cleanup of resources.
     */
    public static void quitDriver() {
        DriverClient.quitDriver();
    }
}
