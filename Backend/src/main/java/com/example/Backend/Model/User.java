package com.example.Backend.Model;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;


public class User {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private HashMap<Timestamp, Email> sentEmails;
    private HashMap<Timestamp, Email> receivedEmails;
    private HashMap<Timestamp, Email> allEmails;
    private HashMap<Timestamp, Email> trash;
    private HashMap<Timestamp, Email> draft;
    private String password;
    private ArrayList<Integer> contacts;

    public User() {
    }


    public User(String firstname, String lastname, String emailAddress, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.emailAddress = emailAddress;
        sentEmails = new HashMap<Timestamp, Email>();
        receivedEmails = new HashMap<Timestamp, Email>();
        allEmails = new HashMap<Timestamp, Email>();
        contacts = new ArrayList<Integer>();
        trash = new HashMap<Timestamp, Email>();
        draft = new HashMap<Timestamp, Email>();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public HashMap<Timestamp, Email> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(HashMap<Timestamp, Email> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public HashMap<Timestamp, Email> getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(HashMap<Timestamp, Email> receivedEmails) {
        this.receivedEmails = receivedEmails;
    }

    public HashMap<Timestamp, Email> getAllEmails() {
        return allEmails;
    }

    public void setAllEmails(HashMap<Timestamp, Email> allEmails) {
        this.allEmails = allEmails;
    }

    public HashMap<Timestamp, Email> getTrash() {
        return trash;
    }

    public void setTrash(HashMap<Timestamp, Email> trash) {
        this.trash = trash;
    }

    public HashMap<Timestamp, Email> getDraft() {
        return draft;
    }

    public void setDraft(HashMap<Timestamp, Email> draft) {
        this.draft = draft;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Integer> contacts) {
        this.contacts = contacts;
    }
}
