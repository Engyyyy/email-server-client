package com.example.Backend.Utility;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class Utility {

    private static ObjectMapper mapper = new ObjectMapper();


    public static final boolean validateUser(String emailAddress) {
        for (HashMap.Entry<String, User> user : UsersList.listOfUsers.entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getEmailAddress().equals(emailAddress)) {
                return true;
            }
        }
        return false;
    }

    public static void loadListOfUsers() throws Exception {
        String path = "src/main/resources/userList.json";
        UsersList.listOfUsers = mapper.readValue(new File(path), new TypeReference<>() {
        });

    }

    public static void update() throws IOException {
        String path = "src/main/resources/userList.json";
        mapper.writeValue(new FileWriter(path), UsersList.listOfUsers);
    }
}
