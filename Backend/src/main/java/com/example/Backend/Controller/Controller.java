package com.example.Backend.Controller;


import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Rearrangments.Arrange;
import com.example.Backend.Register.RegisterServices;
import com.example.Backend.Services.EmailService;
import com.example.Backend.FileManipulation.FileManipulation;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.PriorityQueue;
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
            FileManipulation.createFile(emailAddress, fileName);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/moveToFile")
    public HttpStatus moveToFile(@RequestParam String emailAddress, @RequestParam String from, @RequestParam String to, @RequestParam UUID[] selectedEmails) {
        try {
            usersList.moveEmails(emailAddress, from, to, selectedEmails);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/copyToFile")
    public HttpStatus copyToFile(@RequestParam String emailAddress, @RequestParam String to, @RequestParam String from, @RequestParam UUID[] selectedEmails) {
        try {
            usersList.copyEmails(emailAddress, to, from, selectedEmails);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/send")
    public HttpStatus send(@RequestParam String senderEmail, @RequestParam JSONArray receiversEmails, @RequestParam String subject, @RequestParam String message, @RequestBody MultipartFile[] attachments, @RequestParam int priority) {
        try {
            String[] receivers = new String[receiversEmails.length()];
            for(int i = 0; i < receivers.length; i++) {
                receivers[i] = receiversEmails.getString(i);
            }
            Email email = emailService.createEmail(senderEmail, receivers, subject, message, priority, attachments);
            emailService.sendEmail(email);
            return HttpStatus.OK;

        } catch (Exception fileNotCreated) {
            System.out.println("Exception in send:");
            System.out.println(fileNotCreated);
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @GetMapping("/sort")
    public Email[] sortEmails(@RequestParam String emailAddress, @RequestParam String list, @RequestParam String criteria) {
        try {
            return emailService.sortEmails(emailAddress, list, criteria);
        }
        catch(Exception userNotFound) {
            System.out.println("Exception in sort: USER NOT FOUND");
            System.out.println(userNotFound);
            return new Email[0];
        }
    }

    @DeleteMapping("/deleteFile")
    public HttpStatus deleteFile(@RequestParam String emailAddress, @RequestParam String fileName) {
        try {
            FileManipulation.deleteFile(emailAddress, fileName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/renameFile")
    public HttpStatus renameFile(@RequestParam String emailAddress, @RequestParam String fileName, @RequestParam String newName) {
        try {
            FileManipulation.renameFile(emailAddress, fileName, newName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @DeleteMapping("/deleteEmail")
    public HttpStatus deleteEmail(@RequestParam String emailAddress, @RequestParam String listName, @RequestParam UUID[] emailsId) {
        try {
            usersList.bulkDeletion(emailAddress, listName, emailsId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @GetMapping("/retrieveEmail")
    public HttpStatus retrieveEmail(@RequestParam String emailAddress, @RequestParam UUID[] emailsId) {
        try {
            usersList.bulkRetrieval(emailAddress, emailsId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

}
