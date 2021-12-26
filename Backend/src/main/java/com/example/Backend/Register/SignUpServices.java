package com.example.Backend.Register;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Utility.Utility;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

public class SignUpServices {
    UsersList usersList;

    public SignUpServices() {
        usersList = new UsersList();
    }

    public User signUp(String firstname, String lastname, String emailAddress, String password) throws Exception {
        User newUser = new User(firstname, lastname, emailAddress, password);
        if (Utility.validateUser(newUser.getEmailAddress())) throw new Exception();
        usersList.addUser(emailAddress, password, firstname, lastname);
        return newUser;
    }


}
