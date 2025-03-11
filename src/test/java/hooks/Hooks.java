package hooks;

import base.BaseTest;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.api.ApiClient;
import utils.db.DatabaseClient;
import utils.driver.DriverClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static utils.helper.ReusableMethods.getScreenshot;

public class Hooks {
    private static DatabaseClient databaseClient;
    private static ApiClient apiClient;
    private static List<String> screenshots = new ArrayList<>();

    /**
     * Initializes global clients before all tests run.
     */
    @BeforeAll
    public static void initializeClients() {
        try {
            apiClient = new ApiClient();
            databaseClient = new DatabaseClient();
            System.out.println("Global clients initialized: Driver, ApiClient, and DatabaseClient.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize global clients: " + e.getMessage(), e);
        }
    }

    /**
     * Sets up the test environment before each scenario.
     */
    @Before
    public void setUp() {
        DriverClient.getDriver();
        screenshots.clear();
        Allure.addAttachment("Before Scenario", "Starting test...");
    }

    /**
     * Captures a screenshot and attaches it to the report.
     */
    private void captureScreenshot(Scenario scenario, String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverClient.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", stepName);
        } catch (Exception e) {
            System.err.println("Screenshot capture failed: " + e.getMessage());
        }
    }

    /**
     * Takes a screenshot after each failed step.
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot(scenario, "Failed Step");
        }
    }

    /**
     * Takes a screenshot at the end of a failed scenario and quits the driver.
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot(scenario, "Failed Scenario");
        } else {
            try {
                // Başarılı senaryolar için ekran görüntüsünü sakla
                String screenshotPath = getScreenshot(scenario.getName());
                String relativePath = "../Screenshots/" + new File(screenshotPath).getName();
                scenario.attach(Files.readAllBytes(Paths.get(screenshotPath)), "image/png", "Screenshot");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Allure.addAttachment("After Scenario", "Test completed.");
        BaseTest.quitDriver();
    }
}
