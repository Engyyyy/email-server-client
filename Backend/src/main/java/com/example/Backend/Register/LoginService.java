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
    public User login(String emailAddress, String password) throws Exception {
        User registeredUser = usersList.getListOfUsers().get(emailAddress);
        if(registeredUser == null || !authentication(registeredUser.getPassword(), password)) {
            throw new Exception();
        }
        else {
            return registeredUser;
        }
    }

    private boolean authentication(String registeredPassword, String password) {
        return registeredPassword.equals(password);
    }

//    private UUID getCurrentUser(String emailAddress) {
//        for (HashMap.Entry<UUID, User> user : usersList.getListOfUsers().entrySet()) {
//            System.out.println(user.getKey() + "  :  " + user.getValue());
//            if (user.getValue().getEmailAddress().equals(emailAddress)) {
//                return user.getValue().getId();
//            }
//        }
//        return null;
//    }
}
