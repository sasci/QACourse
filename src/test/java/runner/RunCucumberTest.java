package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"
//                , "html:report/cucumber"
                , "json:cucumber.json"
                , "summary"
                , "de.monochromata.cucumber.report.PrettyReports:report/cucumber"
        }
        ,features = {"src/test/resources"}
        ,glue = {"appium.steps"}
        ,snippets = CAMELCASE
        ,dryRun=false
        ,monochrome=true
        ,strict=true
        ,tags = {"@PI2"}
)
public class RunCucumberTest {
}
