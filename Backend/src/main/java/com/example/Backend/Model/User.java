package com.example.Backend.Model;

import java.util.HashMap;

public class User {
    private Integer id;
    private String name;
    private String emailAddress;
    private EmailsList sentEmails;
    private EmailsList receivedEmails;
    private EmailsList allEmails;
    private UsersList contacts;

    public String getName() {
        return name;
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
    public UsersList getContacts() {
        return contacts;
    }
}
