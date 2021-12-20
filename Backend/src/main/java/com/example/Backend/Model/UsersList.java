package com.example.Backend.Model;

import java.util.HashMap;

public class UsersList {
    private static UsersList usersList;
    HashMap<Integer, User> listOfUsers;

    private UsersList() {
        listOfUsers = new HashMap<>();
    }
    public static UsersList getInstance() {
        if(usersList == null) {
            usersList = new UsersList();
        }
        return usersList;
    }
    public void addUser(User user) {
        listOfUsers.put(user.getId(), user);
    }
    public User getUser(Integer id) {
        return listOfUsers.get(id);
    }

}
