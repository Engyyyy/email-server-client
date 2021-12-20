package com.example.Backend.Model;

import java.util.HashMap;

public class UsersList {
    private static UsersList usersList;
    HashMap<Integer, User> listOfUsers;

    private UsersList() {

    }
    public UsersList getInstance() {
        return usersList;
    }
}
