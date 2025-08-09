package com.CallTaxiService.cucumber.pages;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.CallTaxiService.cucumber.utils.ExtentReport;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ServiceLinksPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	ExtentTest test;
	Logger logger;
	
	public ServiceLinksPage() {}
	
	public ServiceLinksPage(WebDriver driver, ExtentTest test, Logger logger) {
		if (driver == null) {
            throw new IllegalArgumentException("WebDriver passed to ServiceLinksPage is null!");
        }
    	this.driver=driver;
    	wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.test = test;
		this.logger=logger;
		
	}
	
	 private By servicesLink = By.linkText("Services");
	 private By servicesFound = By.id("type");
	
	public boolean launchServicesLink() {
        driver.findElement(servicesLink).click();
        boolean actResult = true;
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(servicesFound));
		ExtentReport.generateReport(driver, test, Status.PASS,"Services link launch is success");
		logger.info("Services link launched");
		}catch(TimeoutException te) {
			actResult = false;
			ExtentReport.generateReport(driver, test, Status.FAIL,"Services link launch is failure");
			logger.error("Services link not launched");
		}
		return actResult;
    }
	
	private WebElement getCabTypeLink(String cabType) {
	    By cabTypeLink = By.linkText(cabType);
	    return driver.findElement(cabTypeLink);
	}

	public boolean linksPresence(String cabType) {
	    try {
	        WebElement ele = getCabTypeLink(cabType);
	        return ele.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

	public boolean labelsCheck(String cabType) {
	    try {
	        WebElement ele = getCabTypeLink(cabType);
	        return ele.getText().equalsIgnoreCase(cabType);
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

	public void clickCabTypeLink(String cabType) {
	    WebElement ele = getCabTypeLink(cabType);
	    ele.click();
	}

	public boolean verifyDetails(String cabType) {
		try {
			By details=By.className(cabType.toLowerCase());
			
	        WebElement heading = driver.findElement(details);
			try {
			wait.until(ExpectedConditions.presenceOfElementLocated(details));
			ExtentReport.generateReport(driver, test, Status.PASS,cabType+" CabType details is visible");
			logger.info(cabType+" details displayed");
			}
			catch(TimeoutException te) {
				ExtentReport.generateReport(driver, test, Status.FAIL,cabType+"CabType link launched is not visible");
				logger.error(cabType+" details not displayed");
			}
	        return heading.isDisplayed();
	    } 
		catch (NoSuchElementException e) {
	        return false;
	    }
	}
}
