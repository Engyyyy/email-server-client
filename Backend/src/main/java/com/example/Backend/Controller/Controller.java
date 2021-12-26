package com.example.Backend.Controller;


import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Register.RegisterServices;
import com.example.Backend.Services.EmailService;
import com.example.Backend.Utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    RegisterServices registerServices;
    @Autowired
    UsersList usersList;

    EmailService emailService = new EmailService();


    @GetMapping("/login")
    public boolean login(@RequestParam String emailAddress, @RequestParam String password) throws Exception {
        try {
            registerServices.register(emailAddress, password);
            return true;
        }
        catch(Exception invalidEmailOrPassword) {
            return false;
        }
    }


    @PostMapping("/signup")
    public HttpStatus signUp(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String emailAddress, @RequestParam String password) throws Exception {
        try {
            registerServices.register(firstname, lastname, emailAddress, password);
            return HttpStatus.CREATED;
        }
        catch(Exception invalidEmailAddress) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/send")
    public void send(@RequestBody Email email) {
        emailService.sendEmail(email);
    }


}
