package com.example.Backend;

import com.example.Backend.Model.*;
import com.example.Backend.Services.EmailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


@SpringBootApplication
public class EmailServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmailServerApplication.class, args);




    }

}
