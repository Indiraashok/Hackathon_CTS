package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExcelUtil {

	//This class provides utility methods to write data into an Excel file using Apache POI library.
    private static final Logger logger =
            LogManager.getLogger(ExcelUtil.class);

    //This method writes the extracted data into an Excel file named "HomeLoanData.xlsx"
    public static void writeData(List<List<String>> data) {

        try {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("HomeLoanData");

            int rowIndex = 0;

            for (List<String> rowData : data) {

                Row row = sheet.createRow(rowIndex++);

                int colIndex = 0;

                for (String value : rowData) {
                    row.createCell(colIndex++).setCellValue(value);
                }
            }

            FileOutputStream fos =
                    new FileOutputStream("HomeLoanData.xlsx");

            workbook.write(fos);
            workbook.close();

            logger.info("Excel File Created Successfully : HomeLoanData.xlsx");

        }
        catch (Exception e) {

            logger.error("Failed To Create Excel File", e);
        }
    }
}