package ui_automation.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    private static final Logger logger = LogManager.getLogger(ExcelUtility.class);
    private static XSSFWorkbook workBook;
    private static XSSFSheet workSheet;
    private static XSSFRow row;
    private static XSSFCell cell;

    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {
            FileInputStream inputStream = new FileInputStream(Path);
            workBook = new XSSFWorkbook(inputStream);
            workSheet = workBook.getSheet(SheetName);
            logger.info("Successfully connect to excel file!");
        } catch (Exception e) {
            logger.error("OOOOPPPSSS! Excel connection failed! Check your System!");
            throw (e);
        }
    }

    public static Object getCellData(int RowNum, int ColNum) throws Exception{
        try{
            cell = workSheet.getRow(RowNum).getCell(ColNum);
            return cell.getStringCellValue();
        }catch (Exception IllegalStateException){
            try{
                cell = workSheet.getRow(RowNum).getCell(ColNum);
                return cell.getNumericCellValue();
            }
            catch (Exception e){
                cell = workSheet.getRow(RowNum).getCell(ColNum);
                return cell.getDateCellValue();
            }
        }
    }

    public static String getCellDataAsString(int RowNum, int ColNum) throws Exception{
        try {
            cell = workSheet.getRow(RowNum).getCell(ColNum);
            return cell.getStringCellValue();
        } catch (Exception e) {
            throw (e);
        }
    }

    public static double getCellDataAsDouble(int RowNum, int ColNum) throws Exception{
        try {
            cell = workSheet.getRow(RowNum).getCell(ColNum);
            return cell.getNumericCellValue();
        } catch (Exception e) {
            throw (e);
        }
    }

    public static void setCellData(String path, String value,  int RowNum, int ColNum) throws Exception {
        try{
            row  = workSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            workBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        }catch(Exception e){

            throw (e);

        }

    }


    public static void createExcelAndWrite(String fileName, String value){
        workBook = new XSSFWorkbook();
        workSheet = workBook.createSheet("FIRST SHEET");
        row = workSheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue(value);
        try (FileOutputStream fos = new FileOutputStream(new File(fileName)))
        {
            workBook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}