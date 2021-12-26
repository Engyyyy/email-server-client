package com.example.Backend;

import com.example.Backend.Model.*;
import com.example.Backend.Services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


@SpringBootApplication
public class EmailServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailServerApplication.class, args);
        UsersList usersList = new UsersList();
       try {
            usersList.loadListOfUsers();
           System.out.println("loaded");
       }catch (Exception e){
            System.out.println("error in loading files");
        }
    }

}
