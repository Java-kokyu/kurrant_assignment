package com.example.kurrant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = KurrantApplication.class)
@SpringBootApplication
public class KurrantApplication {
    public static void main(String[] args) {
        SpringApplication.run(KurrantApplication.class, args);
    }
}
