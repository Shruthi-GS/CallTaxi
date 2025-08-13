package com.Hotel.API.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentReport {
	
	public static void generateReport(ExtentTest test, Status status, String stepMessage) {
	    test.log(status, stepMessage);
	}	
}