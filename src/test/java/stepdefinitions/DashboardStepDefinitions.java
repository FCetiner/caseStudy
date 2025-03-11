package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.qameta.allure.*;
import pages.DashboardPage;
import pages.LoginPage;


public class DashboardStepDefinitions {
    private final DashboardPage dashboardPage;


    public DashboardStepDefinitions()  {
        this.dashboardPage = new DashboardPage();
    }

    @Step("User logs out")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User goes logs out")
    @And("user logs out")
    public void userLogsOut() {
        dashboardPage.logout();
    }
}
