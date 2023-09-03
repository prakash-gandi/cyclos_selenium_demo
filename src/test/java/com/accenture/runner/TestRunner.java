package com.accenture.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.accenture.stepdefinitions", "com.accenture.cucumberHooks"},
        plugin = {"pretty" , "json:target/cucumber-reports/Cucumber.json"
        }

)
public class TestRunner {
}
