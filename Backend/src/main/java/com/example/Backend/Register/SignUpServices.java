package com.example.Backend.Register;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

public class SignUpServices {
    UsersList usersList;

    public SignUpServices(){
        usersList = new UsersList();
    }

    public User signUp(String firstname, String lastname, String emailAddress, String password) throws Exception {
        User newUser = new User(firstname, lastname, emailAddress, password);
        if (!checkUniqueness(newUser.getEmailAddress())) throw new Exception();
        usersList.addUser(newUser);
        return newUser;
    }

    private boolean checkUniqueness(String emailAddress) {
        for (HashMap.Entry<String, User> user : usersList.getListOfUsers().entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getEmailAddress().equals(emailAddress)) {
                return false;
            }
        }
        return true;
    }
}
