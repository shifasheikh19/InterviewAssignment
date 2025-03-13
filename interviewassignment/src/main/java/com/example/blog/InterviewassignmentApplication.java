package com.example.blog; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.example.blog")
public class InterviewassignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewassignmentApplication.class, args);
    }
}