package com.example.Backend.Model;

public class User {
    private String name;
    private String emailAddress;
    private Email[] sentEmails;
    private Email[] receivedEmails;
    private Email[] allEmails;
    private User[] contacts;

    String getName() {
        return name;
    }
    String getEmailAddress() {
        return emailAddress;
    }
    Email[] getSentEmails() {
        return sentEmails;
    }
    Email[] getReceivedEmails() {
        return receivedEmails;
    }
    Email[] getAllEmails() {
        return allEmails;
    }
    User[] getContacts() {
        return contacts;
    }
}
