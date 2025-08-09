package com.CallTaxiService.cucumber.utils;
import java.util.Properties;
import com.CallTaxiService.cucumber.config.ConfigFileReader;
public class ExcelUtils {

	    public static String[] readDataUsingSheetName(String sheetName) throws Exception {
	        Properties prop = ConfigFileReader.readProperty();
	        String filePath = prop.getProperty(sheetName);

	        if (filePath == null) {
	            throw new RuntimeException("No entry found in Configuration.properties for: " + sheetName);
	        }

	        return ExcelReader.readExcelData(filePath);
	    }
	}