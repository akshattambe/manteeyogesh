package org.example.runner.trial;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Trial1.feature"},
        glue = {"org.example.steps.trial", "org.example.config"},
        tags = "@regression",
        plugin = {
                "html:target/generated-report/Trial1",
                "json:target/json/Trial1.json",
                "pretty"
        })
public class Trial1Runner {
}
