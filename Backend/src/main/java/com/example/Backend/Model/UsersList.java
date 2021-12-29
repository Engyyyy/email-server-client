package com.example.Backend.Model;

import com.example.Backend.Services.Factories.ListFactory;
import com.example.Backend.Services.FileManipulation.FileManipulation;
import com.example.Backend.Model.Users.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;


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


    public User getUser(String emailAddress) {
        return listOfUsers.get(emailAddress);
    }


}
