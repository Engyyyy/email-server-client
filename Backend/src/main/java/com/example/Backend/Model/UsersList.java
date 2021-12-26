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
        User user = new User(firstNAme, lastName, emailAddress, password);
        EmailHeader eh = new EmailHeader("aliaa", new String[]{"fdg"}, "dgra", true);
        EmailBody eb = new EmailBody("dfffffffs");
        Email email = new Email(eh, eb);
        user.getSentEmails().put(email.getHeader().getId(), email);
        EmailHeader eh1 = new EmailHeader("aliaa", new String[]{"fdg"}, "dgra", true);
        user.getSentEmails().put(email.getHeader().getId(), email);
        Email email2 = new Email(eh1, eb);
        EmailHeader eh2 = new EmailHeader("aliaa", new String[]{"fdg"}, "dgra", true);
        user.getSentEmails().put(email2.getHeader().getId(), email2);
        Email email3 = new Email(eh2, eb);
        EmailHeader eh3 = new EmailHeader("aliaa", new String[]{"fdg"}, "dgra", true);
        user.getSentEmails().put(email3.getHeader().getId(), email3);
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

        listOfUsers = objectMapper.readValue(new File(path), new TypeReference<>() {
        });

    }

    public void update() throws IOException {
        String path = "src/main/resources/userList.json";
        mapper.writeValue(new FileWriter(path), listOfUsers);
    }
}
