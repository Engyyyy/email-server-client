package com.example.Backend.Model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class UsersList {

    private static HashMap<UUID, User> listOfUsers=  new HashMap<>();


    public void addUser(User user) {
        listOfUsers.put(user.getId(), user);
    }
    public User getUser(UUID id) {
        return listOfUsers.get(id);
    }

    public HashMap<UUID, User> getListOfUsers() {
        return listOfUsers;
    }
}
