package com.zhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.zhi.dao", "com.zhi.operationlog.dao", "com.zhi.listing.mapper"})
public class TemplateWithDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateWithDbApplication.class, args);
        System.out.println("TemplateWithDb Application Is Started...");
    }
}
