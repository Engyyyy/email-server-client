package com.example.Backend.Controller;


import com.example.Backend.Model.Email;
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
    public String login(@RequestParam String emailAddress) throws Exception {
        return registerServices.RegisterServices(emailAddress).toString();
    }


    @PostMapping("/signup")
    public String signUp(@RequestParam String emailAddress, @RequestParam String userName) {
        return registerServices.RegisterServices(emailAddress, userName).toString();
    }

    @PostMapping("/send")
    public void send(@RequestBody Email email) {
        emailService.sendEmail(email);
    }


}
