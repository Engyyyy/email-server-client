package com.example.Backend.Register;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

public class LoginService {

    UsersList usersList;

    public LoginService(){
        usersList = new UsersList();
    }
    public UUID Login(String emailAddress) throws Exception {
        UUID id = getCurrentUser(emailAddress);
        if (id == null) throw new Exception();
        return id;
    }

    private UUID getCurrentUser(String emailAddress) {
        for (HashMap.Entry<UUID, User> user : usersList.getListOfUsers().entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getEmailAddress().equals(emailAddress)) {
                return user.getValue().getId();
            }
        }
        return null;
    }
}
