package com.example.Backend.Model;


import java.util.ArrayList;


public class User {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private ArrayList<Email> sentEmails;
    private ArrayList<Email> receivedEmails;
    private ArrayList<Email> allEmails;
    private ArrayList<Email> trash;
    private ArrayList<Email> draft;
    private String password;
    private ArrayList<Integer> contacts;

    public User() {
    }


    public User(String emailAddress, String password, String firstname,String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.emailAddress = emailAddress;
        sentEmails = new ArrayList<>();
        receivedEmails = new ArrayList<Email>();
        allEmails = new ArrayList<Email>();
        contacts = new ArrayList<Integer>();
        trash = new ArrayList<Email>();
        draft = new ArrayList<Email>();
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

    public ArrayList<Email> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(ArrayList<Email> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public ArrayList<Email> getReceivedEmails() {
        return receivedEmails;
    }

    public void setReceivedEmails(ArrayList<Email> receivedEmails) {
        this.receivedEmails = receivedEmails;
    }

    public ArrayList<Email> getAllEmails() {
        return allEmails;
    }

    public void setAllEmails(ArrayList<Email> allEmails) {
        this.allEmails = allEmails;
    }

    public ArrayList<Email> getTrash() {
        return trash;
    }

    public void setTrash(ArrayList<Email> trash) {
        this.trash = trash;
    }

    public ArrayList<Email> getDraft() {
        return draft;
    }

    public void setDraft(ArrayList<Email> draft) {
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
