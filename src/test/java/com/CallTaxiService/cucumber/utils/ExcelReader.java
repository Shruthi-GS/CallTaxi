
package com.CallTaxiService.cucumber.utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    public static String[] readExcelData(String filePath) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();

        int totalRows = sheet.getPhysicalNumberOfRows();
        XSSFRow firstRow = sheet.getRow(0);

        int columnCount = firstRow.getPhysicalNumberOfCells();
        String[] values = new String[totalRows];

        for (int i = 0; i < totalRows; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                if (columnCount >= 2 && row.getCell(1) != null) {
                    values[i] = formatter.formatCellValue(row.getCell(1)).trim(); 
                } else if (columnCount == 1 && row.getCell(0) != null) {
                    values[i] = formatter.formatCellValue(row.getCell(0)).trim(); 
                }
            }
        }

        workbook.close();
        fis.close();
        return values;
    }
}

