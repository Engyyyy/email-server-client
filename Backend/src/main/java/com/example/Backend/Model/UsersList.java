package com.example.Backend.Model;

import com.example.Backend.Factories.ListFactory;
import com.example.Backend.FileManipulation.FileManipulation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;


@Component
public class UsersList {

    @Autowired
    ListFactory listFactory;
    public static HashMap<String, User> listOfUsers = new HashMap<>();

    public UsersList() {

    }

    public void addUser(String emailAddress, String password, String firstNAme, String lastName) throws Exception {
        FileManipulation.createUserDirectory(emailAddress);
        User user = new User(firstNAme, lastName, emailAddress, password);
        String path = "src/main/resources/DB/" + emailAddress + "/" + "userBasicData.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new FileWriter(path), user.getUserBasicData());
        } catch (Exception e) {
            System.out.println(e);
        }
        listOfUsers.put(emailAddress, user);
    }

    public void moveEmails(String emailAddress, String from, String to, UUID[] selectedEmails) throws Exception {
        try {
            FileManipulation.fillFile(selectedEmails, emailAddress, to, from);
            FileManipulation.removeFromFile(selectedEmails, emailAddress, from);
            FileManipulation.update(emailAddress, from);
            FileManipulation.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void copyEmails(String emailAddress, String to, String from, UUID[] selectedEmails) throws Exception {
        try {
            FileManipulation.fillFile(selectedEmails, emailAddress, to, from);
            FileManipulation.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }


    public User getUser(String emailAddress) {
        return listOfUsers.get(emailAddress);
    }


}
