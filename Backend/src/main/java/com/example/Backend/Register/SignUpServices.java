package com.example.Backend.Register;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import org.springframework.stereotype.Component;

import java.util.HashMap;

public class SignUpServices {
    UsersList usersList;

    public SignUpServices(){
        usersList = UsersList.getInstance();
    }

    public void SignUp(String emailAddress, String userName) throws Exception {
        if (!checkUniqueness(emailAddress)) throw new Exception();
        usersList = UsersList.getInstance();
        int id = usersList.getListOfUsers().size();
        User newUser = new User(id, userName, emailAddress);
        usersList.addUser(newUser);
    }

    private boolean checkUniqueness(String emailAddress) {
        for (HashMap.Entry<Integer, User> user : usersList.getListOfUsers().entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getEmailAddress().equals(emailAddress)) {
                return false;
            }
        }
        return true;
    }
}
