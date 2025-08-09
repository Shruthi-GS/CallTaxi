package com.CallTaxiService.cucumber.hooks;

import java.util.Properties;
import com.CallTaxiService.cucumber.config.ConfigFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.CallTaxiService.cucumber.utils.DriverSetup;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

	public class Hooks extends DriverSetup
	{
		static ExtentSparkReporter spark;
		static ExtentReports extReports;
		public static ExtentTest test;
		public static final Logger logger = LogManager.getLogger(Hooks.class);
		@BeforeAll()
		public static void beforeAll() {
			Properties prop = ConfigFileReader.readProperty();
			spark = new ExtentSparkReporter(prop.getProperty("ExtentReport"));
			extReports = new ExtentReports();
			extReports.attachReporter(spark);
		}

		@AfterAll()
		public static void afterAll() {
			extReports.flush();
		}

		@Before()
		public void before() {
			test = extReports.createTest("Call Taxi");
			launchBrowser();
		}

		@After()
		public void after() throws InterruptedException {
			quitBrowser();
		}
	}