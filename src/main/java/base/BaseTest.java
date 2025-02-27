package base;

import org.openqa.selenium.WebDriver;
import pages.*;
import utils.driverClient.DriverClient;

public class BaseTest {


    private static LoginPage loginPage;
    private static SignupPage signupPage;
    private static DashboardPage dashboardPage;


    /**
     * Initializes and returns the Web driver instance.
     * Ensures a single driver instance is created (Singleton pattern).
     */
    public static WebDriver getDriver()  {
        return DriverClient.getDriver();
    }

    /**
     * Initializes and returns the LoginPage instance.
     */
    public static LoginPage getLoginPage()  {
        if (loginPage == null) {
            loginPage = new LoginPage(getDriver());
        }
        return loginPage;
    }
    /**
     * Initializes and returns the LoginPage instance.
     */
    public static DashboardPage getDashboardPage()  {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage(getDriver());
        }
        return dashboardPage;
    }
    /**
     * Initializes and returns the SignupPage instance.
     */
    public static SignupPage getSignupPage()  {
        if (signupPage == null) {
            signupPage = new SignupPage(getDriver());
        }
        return signupPage;
    }


    /**
     * Quits the Web driver and resets all page instances.
     * Ensures proper cleanup of resources.
     */
    public static void quitDriver() {
        DriverClient.quitDriver();
        loginPage = null;

    }
}
