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

    public UUID SignUp(String emailAddress, String userName) throws Exception {
        if (!checkUniqueness(emailAddress)) throw new Exception();
        User newUser = new User(userName, emailAddress);
        usersList.addUser(newUser);
        return newUser.getId();
    }

    private boolean checkUniqueness(String emailAddress) {
        for (HashMap.Entry<UUID, User> user : usersList.getListOfUsers().entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getEmailAddress().equals(emailAddress)) {
                return false;
            }
        }
        return true;
    }
}
