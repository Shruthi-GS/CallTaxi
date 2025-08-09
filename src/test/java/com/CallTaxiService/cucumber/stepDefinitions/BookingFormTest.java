package com.CallTaxiService.cucumber.stepDefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.CallTaxiService.cucumber.pages.BookingFormPage;
import com.CallTaxiService.cucumber.utils.DriverSetup;
import com.aventstack.extentreports.ExtentTest;
import com.CallTaxiService.cucumber.hooks.Hooks;

public class BookingFormTest{

	    BookingFormPage form;
	    WebDriver driver = Hooks.driver;
		ExtentTest test = Hooks.test;
		Logger logger=Hooks.logger;
	    
	    @Given("Navigate to url by launching the browser")
	    public void  navigate_to_url_by_launching_the_browser() {
	        form=new BookingFormPage(driver,test,logger);
	    }
	    
	    @When("Click on Booking")
	    public void click_on_booking() {
	    	boolean actResult=form.launchBookingLink();
	    	Assert.assertTrue(actResult);
	    }
	    
	    @And("Fill all the fields with valid details from {string} sheet")
	    public void fill_all_the_fields_with_valid_details_from_sheet(String sheetName) {
	    	form.fillFormFromExcel(sheetName);
	    }
	    
	    @And("Click on Book Now")
	    public void click_on_book_now() {
	    	form.submit();
	    	
	    }

	    @And("Enter invalid data in {string} field like {string}")
	    public void enter_invalid_data_in_field_like(String fieldName,String field) {
	    	if(fieldName.equalsIgnoreCase("name"))
	    		form.setName(field);
	    	else if(fieldName.equalsIgnoreCase("email"))
	    		form.setEmail(field);
	    	else if(fieldName.equalsIgnoreCase("phoneNo"))
	    		form.setPhone(field);
	    	else if(fieldName.equalsIgnoreCase("date"))
	    		form.setDate(field);
	    	
	}

	    @Then("Success message with booking details in tabular format should appear")
	    public void success_message_with_booking_details_in_tabular_format_should_appear() {
	        Assert.assertTrue(form.isBookingSuccessful());
	    }

	    @Then("Booking should not happen due to invalid {string}")
	    public void Booking_should_not_happen_due_to_invalid(String field) {
	        Assert.assertFalse(form.isErrorDisplayed(field));
	    }

	    @And("Do not select any option in select trip field")
	    public void do_not_select_any_option_in_select_trip_field() {
	    	form.clearTripSelection();
	    }

	    @And("Leave cab type unselected")
	    public void leave_cab_type_unselected() {
	    	form.clearCabTypeSelection();
	    }

	    @And("^Fill remaining details$")
	    public void  fill_remaining_details(DataTable dataTable) {
	    	form.fillRemainingDetails(dataTable);
	    	
	    }

	    @Then("Error message should appear for unselected {string}")
	    public void error_message_should_appear_for_unselected(String field) {
	        Assert.assertTrue(form.isErrorDisplayed(field));
	    }
	}
