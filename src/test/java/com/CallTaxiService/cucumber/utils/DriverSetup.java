package com.CallTaxiService.cucumber.utils;

import java.util.Properties;

//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.CallTaxiService.cucumber.config.ConfigFileReader;

public class DriverSetup {
	static public WebDriver driver;
	
	public static void launchBrowser() {
		Properties prop = ConfigFileReader.readProperty();
		
		if(prop.getProperty("Browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(prop.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(prop.getProperty("Browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
			System.out.println("Enter  only either chrome or firefox or edge");
		}
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}
	
	public static void quitBrowser() {
		 if (driver != null) {
	            driver.quit();
	        }
	}
}
