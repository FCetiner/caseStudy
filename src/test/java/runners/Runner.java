package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty", "summary",
                "html:test-output/HtmlReports/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:target/failedRerun.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        snippets = CAMELCASE,
        features = "src/test/resources/features",
        monochrome = false,
        glue = {"stepdefinitions", "hooks"},
        tags = "@smoke",
        dryRun = false)
public class Runner {
}
