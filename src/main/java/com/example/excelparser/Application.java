package com.example.excelparser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@SpringBootApplication
@RestController
public class Application {

    @PostMapping("/parseExcel")
    ResponseEntity<String> parseExcel(@RequestPart(name = "fileName") String fileName,
                                      @RequestPart(name = "file") byte[] byteArr) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(byteArr)){
            if(!(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) throw new InvalidFormatException("");
            ExcelParser parser = new ExcelParser(fileName, in);
            return new ResponseEntity<>(parser.getParsedSheet(), HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>("Ошибка чтения файла на сервере. Попробуйте ещё раз.", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (InvalidFormatException e){
            return new ResponseEntity<>("Формат файла недопустим.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
