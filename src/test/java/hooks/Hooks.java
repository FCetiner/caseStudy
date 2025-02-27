package hooks;


import base.BaseTest;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.apiClient.ApiClient;
import utils.databaseClient.DatabaseClient;
import utils.propertyManager.PropertyManager;
import utils.util.Configs;
import utils.driverClient.DriverClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.util.ReusableMethods.getScreenshot;

public class Hooks {
    private static DatabaseClient databaseClient;
    private static ApiClient apiClient;
    private static String environment;
    private static List<String> screenshots = new ArrayList<>();
    private static Map<String, String> configDetails = new HashMap<>();

    /**
     * Initializes global clients before all tests run.
     */
    @BeforeAll
    public static void initializeClients() {
        try {
            apiClient = new ApiClient();
            databaseClient = new DatabaseClient();
            configDetails = Configs.getValidConfig(PropertyManager.getInstance().environmentVariable());
            System.out.println("Global clients initialized: Driver, ApiClient, and DatabaseClient.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize global clients: " + e.getMessage(), e);
        }
    }

    /**
     * Captures screenshots after each step and attaches them to the report.
     *
     * @param scenario the Cucumber scenario
     */
    @BeforeStep
    public void beforeStep(Scenario scenario)   {
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverClient.getDriver()).getScreenshotAs(OutputType.BYTES);
            String stepName = scenario.getName();
            scenario.attach(screenshot, "image/png", stepName);
        }
    }

    /**
     * Sets up the test environment before each scenario.
     */
    @Before
    public void setUp()  {
        DriverClient.getDriver();
        screenshots.clear();
    }

    /**
     * Captures screenshots after each step and attaches them to the report.
     *
     * @param scenario the Cucumber scenario
     */
    @AfterStep
    public void afterStep(Scenario scenario)  {
        if (scenario.isFailed() || !scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverClient.getDriver()).getScreenshotAs(OutputType.BYTES);
            String stepName = scenario.getName();
            scenario.attach(screenshot, "image/png", stepName);
        }
    }

  //  @After
    public void tearDown(Scenario scenario) throws IOException {
//        DatabaseClient.closeConnection();
/*
        if (scenario.isFailed()) {
            System.out.println(scenario.getName() + " senaryosu hata aldı");
            getScreenshot(scenario.getName() + " senaryosu hata aldı");
            final byte[] screenshot = ((TakesScreenshot) DriverClient.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshots");
            BaseTest.quitDriver();
        } else if (scenario.isFailed() == false) {
            try {
                // Ekran görüntüsünü al ve kaydet
                String screenshotPath = getScreenshot(scenario.getName());

                // HTML ve PDF raporlarına ekran görüntüsünü bağla
                String relativePath = "../Screenshots/" + new File(screenshotPath).getName();
                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(relativePath);

                // Cucumber raporuna ekle
                scenario.attach(Files.readAllBytes(Paths.get(screenshotPath)), "image/png", "Ekran Görüntüsü");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

 */
        BaseTest.quitDriver();
    }



    //@AfterStep
    public void addScrennshot(Scenario scenario) throws IOException {
            getScreenshot("after step screenshot");
            final byte[] screenshot = ((TakesScreenshot) DriverClient.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshots");
    }



}


