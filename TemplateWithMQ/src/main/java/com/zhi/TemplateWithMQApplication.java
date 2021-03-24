package com.zhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.zhi.dao"})
public class TemplateWithMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateWithMQApplication.class, args);
        System.out.println("TemplateWithMQ Application Is Started...");
    }
}
