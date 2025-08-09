package com.CallTaxiService.cucumber.stepDefinitions;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import com.CallTaxiService.cucumber.hooks.Hooks;
import com.CallTaxiService.cucumber.pages.ServiceLinksPage;
import com.CallTaxiService.cucumber.utils.DriverSetup;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ServiceLinksTest{
	
	WebDriver driver = Hooks.driver;
	ExtentTest test = Hooks.test;
	Logger logger= Hooks.logger;
	ServiceLinksPage service;
	
	 @Given("Navigate to the url by launching the browser")
	    public void  navigate_to_the_url_by_launching_the_browser() {
	    	service=new ServiceLinksPage(driver,test,logger);
	 }
	 @When("Click on Services")
	    public void click_on_Services() {
		boolean actResult= service.launchServicesLink();
		 Assert.assertTrue(actResult);
	    }
	 
	 @And("Verify the presence of {string} link")
	 public void Verify_the_presence_of_links(String cabType) {
		 Assert.assertTrue(service.linksPresence(cabType));
	 }
	 
	 @And("Verify the {string} label")
	 public void Verify_the_labels(String cabType){
		 Assert.assertTrue(service.labelsCheck(cabType));
	 }
		 
	 
	 @And("Click on {string} cab type link")
	 public void click_on_cab_type_link(String cabType) {
		 service.clickCabTypeLink(cabType);
		
	 }
	 @Then("Verify the details displayed for {string}")
	 public void Verify_the_details_displayed_for(String cabType) {
		 
		 Assert.assertTrue(service.verifyDetails(cabType));
	 }
}
