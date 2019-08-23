package com.example.excelparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@SpringBootApplication
@RestController
public class Application {

    @PostMapping("/parseExcel")
    ResponseEntity<String> parseExcel(@RequestPart(name = "fileName") String fileName,
                    @RequestPart(name = "file") byte[] byteArr) {
        if(!(fileName.endsWith("xls") || fileName.endsWith("xlsx"))) {/*TODO послать ответ клиенту: формат файла недопустим*/}
        File excelFile = new File("src\\main\\resources\\temp\\" + fileName);
        try (FileOutputStream fos = new FileOutputStream(excelFile)){
            if(!excelFile.exists()) excelFile.createNewFile();
            fos.write(byteArr);
        } catch (IOException e){
            //TODO послать ответ клиенту: ошибка чтения/записи файла
            e.printStackTrace();
        }
        return new ResponseEntity<>("TestResponse", HttpStatus.OK);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
