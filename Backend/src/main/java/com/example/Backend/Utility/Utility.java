package com.example.Backend.Utility;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

public class Utility {

    static final UsersList usersList = new UsersList();

    public static final boolean validateUser(UUID id) {
        for (HashMap.Entry<UUID, User> user : usersList.getListOfUsers().entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
