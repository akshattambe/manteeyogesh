package org.example.runner.trial;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Trial2.feature"},
        glue = {"org.example.steps.trial", "org.example.config"},
        tags = "@regression",
        plugin = {
                "html:target/generated-report/Trial2",
                "json:target/json/Trial2.json",
                "pretty"
        })
public class Trial2Runner {
}
