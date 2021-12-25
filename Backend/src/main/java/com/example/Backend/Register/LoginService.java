package com.example.Backend.Register;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import org.springframework.stereotype.Component;

import java.util.HashMap;

public class LoginService {

    UsersList usersList;

    public LoginService(){
        usersList = UsersList.getInstance();
        usersList.currentUser = null;
    }
    public void Login(String emailAddress) throws Exception {
        usersList.currentUser = getCurrentUser(emailAddress);
        if (usersList.currentUser == null) throw new Exception();
    }

    private User getCurrentUser(String emailAddress) {
        for (HashMap.Entry<Integer, User> user : usersList.getListOfUsers().entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getEmailAddress().equals(emailAddress)) {
                return user.getValue();
            }
        }
        return null;
    }
}
