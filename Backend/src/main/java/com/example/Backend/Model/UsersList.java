package com.example.Backend.Model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class UsersList {

    private static HashMap<String, User> listOfUsers=  new HashMap<>();


    public void addUser(User user) {
        listOfUsers.put(user.getEmailAddress(), user);
    }
    public User getUser(String emailAddress) {
        return listOfUsers.get(emailAddress);
    }

    public HashMap<String, User> getListOfUsers() {
        return listOfUsers;
    }
}
