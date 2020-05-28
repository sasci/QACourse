package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src",
        glue = "appium.steps",
        tags = {"@PI2"},
        plugin = {"pretty",
                "html:report/cucumber",
                "json:report/cucumber.json"
                },
        dryRun = false,
        strict = false,
        monochrome = true
)
public class RunCucumberTest {
}
