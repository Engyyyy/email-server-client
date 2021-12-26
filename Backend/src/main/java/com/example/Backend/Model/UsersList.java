package com.example.Backend.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


@Component
public class UsersList {

    private static HashMap<String, User> listOfUsers = new HashMap<>();
    private ObjectMapper mapper;

    public UsersList() {
        mapper = new ObjectMapper();
    }

    public void addUser(String emailAddress, String password, String firstNAme, String lastName) throws Exception {
        User user = new User(firstNAme,lastName,emailAddress,password);
        listOfUsers.put(emailAddress, user);
        update();
    }

    public User getUser(String emailAddress) {
        return listOfUsers.get(emailAddress);
    }

    public HashMap<String, User> getListOfUsers() {
        return listOfUsers;
    }

    public void loadListOfUsers() throws Exception {
        String path = "src/main/resources/userList.json";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            listOfUsers = objectMapper.readValue(new File(path), new TypeReference<>() {
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update() throws IOException {
        String path = "src/main/resources/userList.json";
        mapper.writeValue(new FileWriter(path), listOfUsers);
    }
}
