package com.example.Backend;

import com.example.Backend.Model.*;
import com.example.Backend.Rearrangments.Arrange;
import com.example.Backend.Utility.Utility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


@SpringBootApplication
public class EmailServerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EmailServerApplication.class, args);
        try {
            Utility.loadListOfUsers();
            System.out.println("loaded");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\nerror in loading files");
        }
      /*  Arrange arrange = new Arrange();
        PriorityQueue<Map.Entry<String, Email>> queue = arrange.arrange(UsersList.listOfUsers.get("aliaa.ebrahem.Ahmed").getReceivedEmails());
        System.out.println("aararanged");*/

    }

}
