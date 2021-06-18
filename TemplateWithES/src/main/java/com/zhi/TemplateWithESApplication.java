package com.zhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.zhi.dao"})
public class TemplateWithESApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateWithESApplication.class, args);
        System.out.println("TemplateWithES Application Is Started...");
    }
}
