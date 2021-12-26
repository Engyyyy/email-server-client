package com.example.Backend.Model;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class User {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private HashMap<UUID, Email> sentEmails;
    private HashMap<UUID, Email> receivedEmails;
    private HashMap<UUID, Email> allEmails;
    private HashMap<UUID, Email> trash;
    private HashMap<UUID, Email> draft;
    private String password;
    private ArrayList<Integer> contacts;

    public User() {
    }


    public User(String firstname, String lastname, String emailAddress, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.emailAddress = emailAddress;
        sentEmails = new HashMap<UUID, Email>();
        receivedEmails = new HashMap<UUID, Email>();
        allEmails = new HashMap<UUID, Email>();
        contacts = new ArrayList<Integer>();
        trash = new HashMap<UUID, Email>();
        draft = new HashMap<UUID, Email>();
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

    public HashMap<UUID, Email> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(HashMap<UUID, Email> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public HashMap<UUID, Email> getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(HashMap<UUID, Email> receivedEmails) {
        this.receivedEmails = receivedEmails;
    }

    public HashMap<UUID, Email> getAllEmails() {
        return allEmails;
    }

    public void setAllEmails(HashMap<UUID, Email> allEmails) {
        this.allEmails = allEmails;
    }

    public HashMap<UUID, Email> getTrash() {
        return trash;
    }

    public void setTrash(HashMap<UUID, Email> trash) {
        this.trash = trash;
    }

    public HashMap<UUID, Email> getDraft() {
        return draft;
    }

    public void setDraft(HashMap<UUID, Email> draft) {
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
