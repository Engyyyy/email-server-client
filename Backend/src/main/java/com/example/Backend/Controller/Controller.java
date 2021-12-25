package com.example.Backend.Controller;


import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Register.RegisterServices;
import com.example.Backend.Services.EmailService;
import com.example.Backend.Utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User login(@RequestParam String emailAddress, @RequestParam String password) throws Exception {
        return registerServices.register(emailAddress, password);
    }


    @PostMapping("/signup")
    public User signUp(@RequestParam User newUser) throws Exception {
        return registerServices.register(newUser);
    }

    @PostMapping("/send")
    public void send(@RequestBody Email email) {
        emailService.sendEmail(email);
    }


}
