package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.servingwebcontent.controller.userController;

@SpringBootApplication(scanBasePackages={"com.example.service", "com.example.servingwebcontent"})
//@ComponentScan(basePackageClasses = userController.class)
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
