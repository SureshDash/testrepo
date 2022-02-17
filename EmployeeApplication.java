package com.sg.emp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages="com.sg.emp")
@SpringBootApplication
@EnableCaching
public class EmployeeApplication {

  
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

}
