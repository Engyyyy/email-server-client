package com.example.Backend.Services.Register;

import com.example.Backend.Model.Users.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterServices {

    LoginService loginService;
    SignUpServices signUpServices;
    @Autowired
    UsersList usersList;
    @Autowired
    EmailService emailService;

    public RegisterServices() {
        loginService = new LoginService();
        signUpServices = new SignUpServices();

    }

    public User register(String emailAddress, String password) throws Exception {
        try {
            emailService.checkTrash(emailAddress);
            return loginService.login(emailAddress, password);
        } catch (Exception invalidEmailAddress) {
            System.out.println("Enter a valid e-mail address.");
            throw new Exception();
        }
    }

    public User register(String firstname, String lastname, String emailAddress, String password) throws Exception {
        try {
            User newUser = signUpServices.signUp(firstname, lastname, emailAddress, password);
            return loginService.login(newUser.getEmailAddress(), newUser.getPassword());
        } catch (Exception invalidEmailAddress) {
            System.out.println("This e-mail address is used.\nPlease enter another e-mail address");
            throw new Exception();
        }
    }
}