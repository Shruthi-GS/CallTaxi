package com.CallTaxiService.cucumber.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features=".\\src\\test\\java\\com\\CallTaxiService\\cucumber\\features",
			glue={"com.CallTaxiService.cucumber.stepDefinitions","com.CallTaxiService.cucumber.hooks"},
			plugin = {"pretty","junit:target/cucumber-reports/Cucumber.xml",
					 "pretty","html:target/cucumber-reports/Cucumber.html"},
			tags = ("@Tc3"))

public class CucumberRunnerTests2 {
	
}