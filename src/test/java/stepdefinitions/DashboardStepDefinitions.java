package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.qameta.allure.*;
import pages.DashboardPage;
@Epic("Dashboard")
@Feature("Dashboard")
public class DashboardStepDefinitions {
    private final DashboardPage dashboardPage;


    public DashboardStepDefinitions()  {
        this.dashboardPage = BaseTest.getDashboardPage();
    }

    @Step("User logs out")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User goes logs out")
    @And("user logs out")
    public void userLogsOut() {
        dashboardPage.logout();
    }
}
