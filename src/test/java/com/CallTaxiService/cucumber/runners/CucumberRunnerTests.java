package com.CallTaxiService.cucumber.runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features=".\\src\\test\\java\\com\\CallTaxiService\\cucumber\\features",
glue={"com.CallTaxiService.cucumber.stepDefinitions","com.CallTaxiService.cucumber.hooks"},
plugin = {"pretty","junit:target/cucumber-reports/Cucumber.xml",
		 "pretty","html:target/cucumber-reports/Cucumber.html"})
//tags="@TC_BOOK_002")

public class CucumberRunnerTests extends AbstractTestNGCucumberTests{
	
}
