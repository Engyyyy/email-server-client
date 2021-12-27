package com.example.Backend.Model;

import com.example.Backend.Utility.Utility;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;


@Component
public class UsersList {

    public static HashMap<String, User> listOfUsers = new HashMap<>();

    public UsersList() {

    }

    public void addUser(String emailAddress, String password, String firstNAme, String lastName) throws Exception {
        Utility.createUserDirectory(emailAddress);
        User user = new User(firstNAme, lastName, emailAddress, password);
        String path = "src/main/resources/DB/"+emailAddress+"/"+"userBasicData.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new FileWriter(path), user.getUserBasicData());
        }catch (Exception e){
            System.out.println(e);
        }
       listOfUsers.put(emailAddress, user);
        /*Utility.update();*/
    }

    public User getUser(String emailAddress) {
        return listOfUsers.get(emailAddress);
    }


}
