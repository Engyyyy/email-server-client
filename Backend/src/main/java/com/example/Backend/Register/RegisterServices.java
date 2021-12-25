package com.example.Backend.Register;

import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class RegisterServices {

    LoginService loginService;
    SignUpServices signUpServices;

    public RegisterServices() {
        loginService = new LoginService();
        signUpServices = new SignUpServices();

    }

    public UUID RegisterServices(String emailAddress) throws Exception {
        try {
            return loginService.Login(emailAddress);
        } catch (Exception invalidEmailAddress) {
            System.out.println("Enter a valid e-mail address.");
            throw new Exception();
        }
    }

    public UUID RegisterServices(String emailAddress, String userName) {
        try {
            signUpServices.SignUp(emailAddress, userName);
            return loginService.Login(emailAddress);
        } catch (Exception invalidEmailAddress) {
            System.out.println("This e-mail address is used.\nPlease enter another e-mail address");
        }
        return null;
    }
}
