package com.example.Backend;

import com.example.Backend.Model.Email.Email;
import com.example.Backend.Services.FileManipulation.FileManipulation;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Services.Rearrangments.Arrange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EmailServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailServerApplication.class, args);
        UsersList usersList = new UsersList();

        try {
            FileManipulation.loadListOfUsers();
            System.out.println("loaded");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("\nerror in loading files");
        }
        Email[] res = Arrange.sortBySender(UsersList.listOfUsers.get("aliaa").getAllEmails());
        Email[] res1 = Arrange.sortByContent(UsersList.listOfUsers.get("aliaa").getAllEmails());
        Email[] res2 = Arrange.sortByNumberOfAttachments(UsersList.listOfUsers.get("aliaa").getAllEmails());
        Email[] res3 = Arrange.sortByNumberOfReceivers(UsersList.listOfUsers.get("aliaa").getAllEmails());
        Email[] res4 = Arrange.sortByPriority(UsersList.listOfUsers.get("aliaa").getAllEmails());
        Email[] res5 = Arrange.sortBySubject(UsersList.listOfUsers.get("aliaa").getAllEmails());
        Email[] res6 = Arrange.sortByTimestamp(UsersList.listOfUsers.get("aliaa").getAllEmails());

        System.out.println("lalalala");
    }

}
