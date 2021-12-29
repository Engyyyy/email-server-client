package com.example.Backend.Services.Register;

import com.example.Backend.Model.Users.User;
import com.example.Backend.Model.UsersList;


public class LoginService {


    public LoginService() {

    }

    public User login(String emailAddress, String password) throws Exception {
        User registeredUser = UsersList.listOfUsers.get(emailAddress);
        if (registeredUser == null || !authentication(registeredUser.getPassword(), password)) {
            throw new Exception();
        } else {
            return registeredUser;
        }
    }

    private boolean authentication(String registeredPassword, String password) {
        return registeredPassword.equals(password);
    }


}
