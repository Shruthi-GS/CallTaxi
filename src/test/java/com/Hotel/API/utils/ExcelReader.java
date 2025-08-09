//
//package com.Hotel.API.utils;
//
//import java.io.FileInputStream;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;
//
//public class ExcelReader {
//
//    public static String[] readExcelData(String filePath) throws Exception {
//        FileInputStream fis = new FileInputStream(filePath);
//        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheetAt(0);
//        DataFormatter formatter = new DataFormatter();
//
//        int totalRows = sheet.getPhysicalNumberOfRows();
//        XSSFRow firstRow = sheet.getRow(0);
//
//        int columnCount = firstRow.getPhysicalNumberOfCells();
//        String[] values = new String[totalRows];
//
//        for (int i = 0; i < totalRows; i++) {
//            XSSFRow row = sheet.getRow(i);
//            if (row != null) {
//                if (columnCount >= 2 && row.getCell(1) != null) {
//                    values[i] = formatter.formatCellValue(row.getCell(1)).trim(); 
//                } else if (columnCount == 1 && row.getCell(0) != null) {
//                    values[i] = formatter.formatCellValue(row.getCell(0)).trim(); 
//                }
//            }
//        }
//
//        workbook.close();
//        fis.close();
//        return values;
//    }
//}
//
package com.Hotel.API.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Hotel.API.config.Configuration;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> getUserData(String sheetName, int rowNumber) {
        Map<String, String> data = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(Configuration.EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            Row dataRow = sheet.getRow(rowNumber);

            DataFormatter formatter = new DataFormatter(); // handles numeric-to-string conversion

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                String key = formatter.formatCellValue(headerRow.getCell(i));
                String value = formatter.formatCellValue(dataRow.getCell(i)); // works for both String and Numeric
                data.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
