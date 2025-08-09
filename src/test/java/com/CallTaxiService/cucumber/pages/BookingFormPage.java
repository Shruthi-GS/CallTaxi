package com.CallTaxiService.cucumber.pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.CallTaxiService.cucumber.utils.ExcelUtils;
import com.CallTaxiService.cucumber.utils.ExtentReport;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.datatable.DataTable;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BookingFormPage {

	    private WebDriver driver;
	    private WebDriverWait wait;
		ExtentTest test;
		Logger logger;
	    
	    public BookingFormPage() {}
	    
	    public BookingFormPage(WebDriver driver, ExtentTest test, Logger logger) {
	    	if (driver == null) {
	            throw new IllegalArgumentException("WebDriver passed to BookingFormPage is null!");
	        }
	    	this.driver=driver;
	    	wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			this.test = test;
			this.logger=logger;
	    }
	    
	    private By bookingLink = By.linkText("Booking");
	    private By bookingFound = By.id("details");
	    private By nameField = By.id("fullname");
	    private By emailField = By.id("email");
	    private By phoneField = By.id("phonenumber");
	    private By passengerCountField = By.id("passenger");
	    private By longTripSelect = By.id("long");
	    private By localTripSelect = By.id("local");
	    private By cabTypeSelect = By.id("cabselect");
	    private By carTypeSelect = By.name("cabType");
	    private By dateField = By.id("pickupdate");
	    private By timeField = By.id("pickuptime");
	    private By oneWaySelect = By.id("oneway");
	    private By roundTripSelect = By.id("roundtrip");
	    private By bookNowButton = By.id("submitted");
	    private By successMessage = By.id("confirm");
	    private By invalidDataError = By.xpath("//table[@id=\"display\"]//td[contains(text(),'Name')]/following-sibling::td");
	    private By tripError = By.id("invalidtrip"); 
	    private By cabTypeError = By.id("invalidcab"); 

	    public boolean launchBookingLink() {
	       WebElement ele= driver.findElement(bookingLink);
	       ele.click();
	       boolean actResult = true;
			try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(bookingFound));
			ExtentReport.generateReport(driver, test, Status.PASS,"Booking link launch is success");
			logger.info("Booking link launched");
			}catch(TimeoutException te) {
				actResult = false;
				ExtentReport.generateReport(driver, test, Status.FAIL,"Booking link launch is failure");
				logger.error("Booking link launched");
			}
			return actResult;
	    }

	    public void fillRemainingDetails(DataTable dataTable) {
	    	try {
	    	 List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		        Map<String, String> row = data.get(0);

		        if (row.containsKey("Name")) setName(row.get("Name"));
		        if (row.containsKey("Email")) setEmail(row.get("Email"));
		        if (row.containsKey("PhoneNo")) setPhone(row.get("PhoneNo"));
		        if (row.containsKey("PassengerCount")) setPassengerCount(row.get("PassengerCount"));
		        if (row.containsKey("Trip")) setTrip(row.get("Trip"));
		        if (row.containsKey("CabType")) setCabType(row.get("CabType"));
		        if (row.containsKey("CarType")) setCarType(row.get("CarType"));
		        if (row.containsKey("Date")) setDate(row.get("Date"));
		        if (row.containsKey("Time")) setTime(row.get("Time"));
		        if (row.containsKey("TripType")) setTripType(row.get("TripType"));
		        		        
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		logger.error("Remaining details not filled");
	    		}
	    }
	    public void fillFormFromExcel(String sheetName) {
			
	    	    try {
	    	    	 String[] data = ExcelUtils.readDataUsingSheetName(sheetName);

	    	        if (data[0] != null) setName(data[0]);
	    	        if (data[1] != null) setEmail(data[1]);
	    	        if (data[2] != null) setPhone(data[2]);
	    	        if (data[3] != null) setPassengerCount(data[3]);
	    	        if (data[4] != null) setTrip(data[4]);
	    	        if (data[5] != null) setCabType(data[5]);
	    	        if (data[6] != null) setCarType(data[6]);
	    	        if (data[7] != null) setDate(data[7]);
	    	        if (data[8] != null) setTime(data[8]);
	    	        if (data[9] != null) setTripType(data[9]);
	    	        
	    	    } catch (Exception e) {
	    	        e.printStackTrace();
	    	        logger.error("All details not filled");
	    	    } 
	    	}
	    public void setName(String name) {
	    	
	        driver.findElement(nameField).clear();
	        driver.findElement(nameField).sendKeys(name);
	       
	    }
	    public void setEmail(String email) {
	    
	        driver.findElement(emailField).clear();
	        driver.findElement(emailField).sendKeys(email);
	        
	    }
	    public void setPhone(String phone) {
	    	
	        driver.findElement(phoneField).clear();
	        driver.findElement(phoneField).sendKeys(phone);
	    }
	    public void setPassengerCount(String count) {
	    	new Select(driver.findElement(passengerCountField)).selectByVisibleText(count);
	    }
	    public void setTrip(String tripOption) {
	    	if(tripOption.equalsIgnoreCase("local trip"))
	        driver.findElement(localTripSelect).click();
	    	else
	    	driver.findElement(longTripSelect).click();
	    }
	    public void setCabType(String cabType) {
	        new Select(driver.findElement(cabTypeSelect)).selectByVisibleText(cabType);
	    }
	    public void setCarType(String type) {
	    	 new Select(driver.findElement(carTypeSelect)).selectByVisibleText(type);
	    }
	    public void setDate(String date) {
	        driver.findElement(dateField).clear();
	        try {
	            Date parsedDate = null;
	            SimpleDateFormat inputFormat;
	            if (date.matches("\\d{2}/\\d{2}/\\d{2}")) {
	                inputFormat = new SimpleDateFormat("dd/MM/yy");
	            } else if (date.matches("\\d{1,2}/\\d{1,2}/\\d{2}")) {
	                inputFormat = new SimpleDateFormat("MM/dd/yy");
	            } else {
	                throw new IllegalArgumentException("Unsupported date format");
	            }

	            inputFormat.setLenient(false);
	            Calendar cal = Calendar.getInstance();
	            cal.set(Calendar.YEAR, 2000);
	            inputFormat.set2DigitYearStart(cal.getTime());
	            parsedDate = inputFormat.parse(date);
	            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	            String formattedDate = outputFormat.format(parsedDate);

	            WebElement ele = driver.findElement(dateField);
	            ele.clear();
	            ele.sendKeys(formattedDate);

	        } catch (Exception e) {
	            System.out.println("Date parsing failed: " + e.getMessage());
	        }
	    }
	    public void setTime(String time) {
	        driver.findElement(timeField).clear();
	        driver.findElement(timeField).sendKeys(time);
	    }
	    public void setTripType(String type) {
	        if (type.equalsIgnoreCase("one way")) {
	            WebElement ele1=driver.findElement(oneWaySelect);
	            ele1.click();
	        } else if (type.equalsIgnoreCase("round trip")) {
	        	 WebElement ele2=driver.findElement(roundTripSelect);
	        	 ele2.click();
	        }
	    }
	    public void submit() {
	    	WebElement ele=driver.findElement(bookNowButton);
	    	ele.click();

	    }
	    public boolean isBookingSuccessful() {
	        boolean actResult = true;
			try {
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
				ExtentReport.generateReport(driver, test, Status.PASS,"Success message is displayed. Booking successful!");
				logger.info("Booking confirmed");
			}catch(TimeoutException te) {
				actResult = false;
				ExtentReport.generateReport(driver, test, Status.FAIL,"Success message is not displayed. Failed booking!");
				logger.error("Booking failed");
			}
			return actResult;
	    }
	    public boolean isErrorDisplayed(String field) {
	    	 boolean actResult=false;
	    	 try {
	    		
	    	        if (field.equalsIgnoreCase("Name") || field.equalsIgnoreCase("Email") || field.equalsIgnoreCase("phoneno") || field.equalsIgnoreCase("date")) {
	    	        	actResult = driver.findElement(invalidDataError).isDisplayed();
	    	        	if(actResult) {
	    	        		ExtentReport.generateReport(driver, test, Status.FAIL,"No error message for invalid "+field+" entry!");
	    	        		logger.error("No error message for invalid "+field+" entry!");
	    	        	}else {
	    	        		ExtentReport.generateReport(driver, test, Status.PASS,"Error message displayed for invalid "+field+" entry!");
	    	        		logger.info("Error message displayed for invalid "+field+" entry!");
	    	        	}
	    	        }
	        	else if(field.equalsIgnoreCase("trip")) {
	        		wait.until(ExpectedConditions.visibilityOfElementLocated(tripError));
	    	        actResult = driver.findElement(tripError).isDisplayed();
    	        	if(actResult) {
    	        		ExtentReport.generateReport(driver, test, Status.PASS,"Error message is displayed for unselected trip!");
    	        		logger.info("Error message is displayed for unselected trip!");
    	        	}else {
    	        		ExtentReport.generateReport(driver, test, Status.FAIL,"No Error message displayed for unselected trip!");
    	        		logger.error("No Error message is displayed for unselected trip!");
    	        	}
	        	}
	        	else if(field.equalsIgnoreCase("cabtype")) {
	        		 wait.until(ExpectedConditions.visibilityOfElementLocated(cabTypeError));
	        		 actResult=driver.findElement(cabTypeError).isDisplayed();
	    	        if(actResult) {
    	        		ExtentReport.generateReport(driver, test, Status.PASS,"Error message is displayed for unselected cabtype!");
    	        		logger.info("Error message is displayed for unselected cabtype!");
    	        	}else {
    	        		ExtentReport.generateReport(driver, test, Status.FAIL,"No Error message displayed for unselected cabtype!");
    	        		logger.error("No Error message is displayed for unselected cabtype!");
    	        	}
	        	}
	    	 } catch (NoSuchElementException e) {
	    		 ExtentReport.generateReport(driver, test, Status.PASS,"Error message displayed for blank "+field+" entry!");
	    		 logger.info("Error message displayed for blank "+field+" entry!");
	    	        return false;
	    	    }
	        		return actResult;
	    }
	    public boolean clearTripSelection() {
	       if(driver.findElement(roundTripSelect).isSelected()||driver.findElement(oneWaySelect).isSelected())
	    	   return false;
	       return true;
	    }
	    public boolean clearCabTypeSelection() {
	    	 if(driver.findElement(cabTypeSelect).isSelected())
		    	   return false;
		       return true;	    
		     }
}
