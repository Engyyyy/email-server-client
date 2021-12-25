package com.example.Backend.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private EmailsList sentEmails;
    private EmailsList receivedEmails;
    private EmailsList allEmails;
    private EmailsList trash;
    private EmailsList draft;
    private String password;
    private List<Integer> contacts;

    public void initialize() {
        sentEmails = new EmailsList();
        receivedEmails = new EmailsList();
        allEmails = new EmailsList();
        contacts = new ArrayList<>();
        trash = new EmailsList();
        draft = new EmailsList();
    }

    public EmailsList getDraft() {
        return draft;
    }

    public EmailsList getTrash() {
        return trash;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public EmailsList getSentEmails() {
        return sentEmails;
    }

    public EmailsList getReceivedEmails() {
        return receivedEmails;
    }

    public EmailsList getAllEmails() {
        return allEmails;
    }

    public String getPassword() {
        return password;
    }

    public List<Integer> getContacts() {
        return contacts;
    }
}
