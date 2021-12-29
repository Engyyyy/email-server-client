package com.example.Backend;

import com.example.Backend.Services.FileManipulation.FileManipulation;
import com.example.Backend.Model.UsersList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EmailServerApplication {

    public static void main(String[] args)  {
        SpringApplication.run(EmailServerApplication.class, args);
        UsersList usersList = new UsersList();
        try {
            FileManipulation.loadListOfUsers();
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
