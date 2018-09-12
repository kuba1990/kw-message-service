package com.bi.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MicroserviceConfig.class)
public class DiMessageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiMessageServiceApplication.class, args);
    }
}
