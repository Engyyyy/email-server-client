package com.example.Backend.Model;

import com.example.Backend.Factories.ListFactory;
import com.example.Backend.Utility.Utility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;


@Component
public class UsersList {

    @Autowired
    ListFactory listFactory;
    public static HashMap<String, User> listOfUsers = new HashMap<>();

    public UsersList() {

    }

    public void addUser(String emailAddress, String password, String firstNAme, String lastName) throws Exception {
        Utility.createUserDirectory(emailAddress);
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

    public void moveEmails(String emailAddress, String from, String to, Email[] selectedEmails) throws Exception {
        fillFile(selectedEmails, emailAddress, to);
        removeFromFile(selectedEmails, emailAddress, from);
        try {
            Utility.update(emailAddress, from);
            Utility.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void copyEmails(String emailAddress, String to, Email[] selectedEmails) throws Exception {
        fillFile(selectedEmails, emailAddress, to);
        try {
            Utility.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void fillFile(Email[] emails, String emailAddress, String to) throws Exception {
        if (listOfUsers.get(emailAddress).getOtherFiles().containsKey(to)) {
            for (Email i : emails) {
                listOfUsers.get(emailAddress).getOtherFiles().get(to).put(i.getHeader().getId(), i);
            }
        } else throw new Exception();
    }

    public void removeFromFile(Email[] emails, String emailAddress, String from) {
        if (listOfUsers.get(emailAddress).getOtherFiles().containsKey(from)) {
            for (Email i : emails) {
                listOfUsers.get(emailAddress).getOtherFiles().get(from).put(i.getHeader().getId(), i);
            }
        } else {
            HashMap<UUID, Email> list = listFactory.getList(from, emailAddress);
            for (Email i : emails) {
                list.remove(i.getHeader().getId());
            }
            listFactory.setList(from, emailAddress, list);
        }
    }

    public User getUser(String emailAddress) {
        return listOfUsers.get(emailAddress);
    }


}
