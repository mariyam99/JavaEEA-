package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, ErrorMvcAutoConfiguration.class })
public class EeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EeaApplication.class, args);
    }

}
