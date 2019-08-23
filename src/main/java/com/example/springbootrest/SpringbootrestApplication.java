package com.example.springbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@SpringBootApplication
@RestController
public class SpringbootrestApplication {

    @PostMapping("/parseExcel")
    void parseExcel(@RequestPart(name = "fileName") String fileName,
                    @RequestPart(name = "file") byte[] byteArr) {
        if(!(fileName.endsWith("xls") || fileName.endsWith("xlsx"))) return; //TODO послать ответ клиенту: формат файла недопустим
        File excelFile = new File("src\\main\\resources\\temp\\" + fileName);
        try (FileOutputStream fos = new FileOutputStream(excelFile)){
            if(!excelFile.exists()) excelFile.createNewFile();
            fos.write(byteArr);
        } catch (IOException e){
            e.printStackTrace(); //TODO послать ответ клиенту: ошибка чтения/записи файла
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootrestApplication.class, args);
    }

}
