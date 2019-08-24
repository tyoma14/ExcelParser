package com.example.excelparser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ExcelParser {

    private ExcelExtractor extractor;

    public ExcelParser(String fileName, ByteArrayInputStream in) throws IOException{
        Workbook workbook;
        if(fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(in);
            extractor = new XSSFExcelExtractor((XSSFWorkbook) workbook);
        }
        else if(fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(in, false);
            extractor = new org.apache.poi.hssf.extractor.ExcelExtractor((HSSFWorkbook) workbook);
        }
    }

    public String getParsedSheet() {
        return extractor.getText();
    }
}
