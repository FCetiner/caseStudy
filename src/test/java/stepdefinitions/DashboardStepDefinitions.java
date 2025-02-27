package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import pages.DashboardPage;

public class DashboardStepDefinitions {
    private final DashboardPage dashboardPage;


    public DashboardStepDefinitions()  {
        this.dashboardPage = BaseTest.getDashboardPage();
    }

    @And("user logs out")
    public void userLogsOut() {
        dashboardPage.logout();
    }
}
