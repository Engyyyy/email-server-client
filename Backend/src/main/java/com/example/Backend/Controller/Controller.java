package com.example.Backend.Controller;


import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Services.ContactServices;
import com.example.Backend.Services.Filter.Filter;
import com.example.Backend.Services.Register.RegisterServices;
import com.example.Backend.ResponseObjects.ResponseEmail;
import com.example.Backend.Services.EmailService;
import com.example.Backend.Services.FileManipulation.FileManipulation;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    RegisterServices registerServices;
    @Autowired
    UsersList usersList;
    @Autowired
    EmailService emailService;
    @Autowired
    ContactServices contactServices;
    @Autowired
    Filter filter;

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
            emailService.moveEmails(emailAddress, from, to, selectedEmails);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/copyToFile")
    public HttpStatus copyToFile(@RequestParam String emailAddress, @RequestParam String to, @RequestParam String from, @RequestParam UUID[] selectedEmails) {
        try {
            emailService.copyEmails(emailAddress, to, from, selectedEmails);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/send")
    public HttpStatus send(@RequestParam String senderEmail, @RequestParam JSONArray receiversEmails, @RequestParam String subject, @RequestParam String message, @RequestBody MultipartFile[] attachments, @RequestParam String priority) {
        try {
            String[] receivers = new String[receiversEmails.length()];
            for(int i = 0; i < receivers.length; i++) {
                receivers[i] = receiversEmails.getString(i);
            }
            int integerPriority = emailService.getIntegerPriority(priority);
            Email email = emailService.createEmail(senderEmail, receivers, subject, message, integerPriority, attachments);
            emailService.sendEmail(email);
            return HttpStatus.OK;

        } catch (Exception fileNotCreated) {
            System.out.println("Exception in send:");
            System.out.println(fileNotCreated);
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @GetMapping("/getEmails")
    public ArrayList<ResponseEmail> getEmails(@RequestParam String emailAddress, @RequestParam String list, @RequestParam String criteria, @RequestParam int pageNumber, @RequestParam int itemsPerPage) {
        try {
            return emailService.getEmails(emailAddress, list, criteria, pageNumber, itemsPerPage);
        } catch (Exception exception) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/getLength")
    public int getLength(@RequestParam String emailAddress, @RequestParam String list) {
        try {
            return emailService.getEmailsLength(emailAddress, list);
        } catch (Exception exception) {
            System.out.println("User Not Found");
            return 0;
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
            System.out.println(Arrays.toString(e.getStackTrace()));
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @DeleteMapping("/deleteEmail")
    public HttpStatus deleteEmail(@RequestParam String emailAddress, @RequestParam String listName, @RequestParam UUID[] emailsId) {
        try {
            emailService.bulkDeletion(emailAddress, listName, emailsId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @GetMapping("/retrieveEmail")
    public HttpStatus retrieveEmail(@RequestParam String emailAddress, @RequestParam UUID[] emailsId) {
        try {
            emailService.bulkRetrieval(emailAddress, emailsId);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/saveToDraft")
    public HttpStatus saveToDraft(@RequestParam String senderEmail, @RequestParam String[] receiversEmails, @RequestParam String subject, @RequestParam String message, @RequestBody MultipartFile[] attachments, @RequestParam String priority) {
        try {
            int integerPriority = emailService.getIntegerPriority(priority);
            Email email = emailService.createEmail(senderEmail, receiversEmails, subject, message, integerPriority, attachments);
            emailService.saveToDraft(email);
            return HttpStatus.OK;
        } catch (Exception fileNotCreated) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/createContact")
    public HttpStatus createContact(@RequestParam String name, @RequestParam String userEmailAddress) {
        try {
            contactServices.createContact(name, userEmailAddress);
            return HttpStatus.OK;
        } catch (Exception fileNotCreated) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }
    @PostMapping("/createContactAndAddEmails")
    public HttpStatus createContactAndAddEmails(@RequestParam String name, @RequestParam String userEmailAddress,@RequestParam String[] emailAddresses) {
        try {
            UUID contactId = contactServices.createContact(name, userEmailAddress).getId();
            contactServices.addEmailAddressesToContact(emailAddresses,userEmailAddress,contactId);
            return HttpStatus.OK;
        } catch (Exception fileNotCreated) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/addEmailAddressesToContact")
    public HttpStatus addEmailAddressesToContact(@RequestParam String[] emailAddresses, @RequestParam String userEmailAddress, @RequestParam UUID contactId) {
        try {
            contactServices.addEmailAddressesToContact(emailAddresses, userEmailAddress, contactId);
            return HttpStatus.OK;
        } catch (Exception fileNotCreated) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @DeleteMapping("/deleteContact")
    public HttpStatus deleteContact(@RequestParam String userEmailAddress, @RequestParam UUID contactId) {
        try {
            contactServices.deleteContact(userEmailAddress, contactId);
            return HttpStatus.OK;
        } catch (Exception fileNotCreated) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/renameContact")
    public HttpStatus renameContact(@RequestParam String emailAddress, @RequestParam UUID contactId, String newName) {
        try {
            contactServices.renameContact(emailAddress, contactId, newName);
            return HttpStatus.OK;
        } catch (Exception fileNotCreated) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/filter")
    public HttpStatus filter(@RequestParam String emailAddress, @RequestParam String fileName, @RequestParam String criteria) {
        try {
            filter.filter(emailAddress, fileName, criteria);
            return HttpStatus.OK;
        } catch (Exception fileNotCreated) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @GetMapping("/getContacts")
    public ArrayList<Contact> getContacts(@RequestParam String emailAddress) {
        return contactServices.getContacts(emailAddress);
    }


}
