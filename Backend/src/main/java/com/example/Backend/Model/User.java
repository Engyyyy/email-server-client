package com.example.Backend.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String userName;
    private String emailAddress;
    private EmailsList sentEmails;
    private EmailsList receivedEmails;
    private EmailsList allEmails;
    private EmailsList trash;
    private EmailsList draft;
    private List<Integer> contacts;

    public User(String userName, String emailAddress) {
        this.id = UUID.randomUUID();;
        this.userName = userName;
        this.emailAddress = emailAddress;
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

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
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

    public List<Integer> getContacts() {
        return contacts;
    }
}
