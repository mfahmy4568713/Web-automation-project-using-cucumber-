package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/features",
        glue = "stepDefinations",
        tags = "@regression",
        plugin = {"pretty","html:target/reports/report.html"}
)

public class TestRunner {
}
