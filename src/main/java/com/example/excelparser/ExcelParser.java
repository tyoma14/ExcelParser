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
        if(fileName.endsWith(".xlsx")) {
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            extractor = new XSSFExcelExtractor(workbook);
        }
        else if(fileName.endsWith(".xls")) {
            HSSFWorkbook workbook = new HSSFWorkbook(in, false);
            extractor = new org.apache.poi.hssf.extractor.ExcelExtractor(workbook);
        }
    }

    public String getParsedBook() {
        String text = extractor.getText();
        //для браузера заменяем знаки "<" и ">" на спецсимволы
        text = text.replaceAll("<","&lt;")
                   .replaceAll(">", "&gt;");
        return text;
    }
}
