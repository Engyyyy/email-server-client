package com.example.Backend.Model;


import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private String emailAddress;
    private EmailsList sentEmails;
    private EmailsList receivedEmails;
    private EmailsList allEmails;
    private List<Integer> contacts;

//    public User(Integer id, String name, String emailAddress) {
//        this.id = id;
//        this.name = name;
//        this.emailAddress = emailAddress;
//
//        sentEmails = new EmailsList();
//        receivedEmails = new EmailsList();
//        allEmails = new EmailsList();
//        contacts = new ArrayList<>();
//    }

    public Integer getId() {
        return id;
    }
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
    public List<Integer> getContacts() {
        return contacts;
    }
}
