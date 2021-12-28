package com.example.Backend.Controller;


import com.example.Backend.Model.Attachment;
import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Register.RegisterServices;
import com.example.Backend.Services.EmailService;
import com.example.Backend.Utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@RestController
public class Controller {
    EmailService emailService = new EmailService();
    @Autowired
    RegisterServices registerServices;
    @Autowired
    UsersList usersList;
    @GetMapping("/login")
    public boolean login(@RequestParam String emailAddress, @RequestParam String password) throws Exception {
        try {
            registerServices.register(emailAddress, password);
            return true;
        } catch (Exception invalidEmailOrPassword) {
            return false;
        }
    }


    @PostMapping("/signup")
    public HttpStatus signUp(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String emailAddress, @RequestParam String password) throws Exception {
        try {
            registerServices.register(firstname, lastname, emailAddress, password);
            return HttpStatus.CREATED;
        } catch (Exception invalidEmailAddress) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/createFile")
    public HttpStatus createFolder(@RequestParam String emailAddress, @RequestParam String fileName) {
        try {
            Utility.createFile(emailAddress, fileName);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/moveToFile")
    public HttpStatus moveToFile(@RequestParam String emailAddress, @RequestParam String from, @RequestParam String to, @RequestParam Email[] selectedEmails) {
        try {
            usersList.moveEmails(emailAddress, from, to, selectedEmails);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/copyToFile")
    public HttpStatus copyToFile(@RequestParam String emailAddress, @RequestParam String to, @RequestParam Email[] selectedEmails) {
        try {
            usersList.copyEmails(emailAddress, to, selectedEmails);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/send")
    public HttpStatus send(@RequestParam String senderEmail, @RequestParam String[] receiversEmails, @RequestParam String subject, @RequestParam String message, @RequestBody MultipartFile[] attachments, @RequestParam String priority) {
        try {
            Email email = emailService.createEmail(senderEmail, receiversEmails, subject, message, priority, attachments);
            emailService.sendEmail(email);
            return HttpStatus.OK;

        }
        catch (Exception fileNotCreated) {
            System.out.println("Exception in send:");
            System.out.println(fileNotCreated);
            return HttpStatus.NOT_ACCEPTABLE;
        }

        //emailService.sendEmail(email);
    }
}
