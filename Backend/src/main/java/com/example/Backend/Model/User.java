package com.example.Backend.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class User {
    private UserBasicData userBasicData;
    private HashMap<UUID, Email> sentEmails;
    private HashMap<UUID, Email> receivedEmails;
    private HashMap<UUID, Email> allEmails;
    private HashMap<UUID, Email> trash;
    private HashMap<UUID, Email> draft;
    private ArrayList<String> contacts;
    private HashMap<String, HashMap<UUID, Email>> otherFiles;

    public User() {
    }

    public User(String emailAddress) {
        sentEmails = new HashMap<UUID, Email>();
        receivedEmails = new HashMap<UUID, Email>();
        allEmails = new HashMap<UUID, Email>();
        contacts = new ArrayList<String>();
        trash = new HashMap<UUID, Email>();
        draft = new HashMap<UUID, Email>();
        userBasicData = new UserBasicData();
        userBasicData.setEmailAddress(emailAddress);
        otherFiles = new HashMap<String, HashMap<UUID, Email>>();
    }

    public UserBasicData getUserBasicData() {
        return userBasicData;
    }

    public void setUserBasicData(UserBasicData userBasicData) {
        this.userBasicData = userBasicData;
    }

    public User(String firstname, String lastname, String emailAddress, String password) {
        this.userBasicData = new UserBasicData(firstname, lastname, emailAddress, password);
        sentEmails = new HashMap<UUID, Email>();
        receivedEmails = new HashMap<UUID, Email>();
        allEmails = new HashMap<UUID, Email>();
        contacts = new ArrayList<String>();
        trash = new HashMap<UUID, Email>();
        draft = new HashMap<UUID, Email>();
        otherFiles = new HashMap<String, HashMap<UUID, Email>>();

    }

    public String getFirstname() {
        return this.userBasicData.getFirstname();
    }

    public void setFirstname(String firstname) {
        userBasicData.setFirstname(firstname);
    }

    public String getLastname() {
        return this.userBasicData.getLastname();
    }

    public void setLastname(String lastname) {
        this.userBasicData.setLastname(lastname);
    }

    public String getEmailAddress() {
        return this.userBasicData.getEmailAddress();
    }

    public void setEmailAddress(String emailAddress) {
        this.userBasicData.setEmailAddress(emailAddress);
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

    public void setList(String listName, HashMap<UUID, Email> list) {
        this.otherFiles.remove(listName);
        this.otherFiles.put(listName, list);
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

    public HashMap<String, HashMap<UUID, Email>> getOtherFiles() {
        return otherFiles;
    }

    public void setOtherFiles(HashMap<String, HashMap<UUID, Email>> otherFiles) {
        this.otherFiles = otherFiles;
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
        return this.userBasicData.getPassword();
    }

    public void setPassword(String password) {
        this.userBasicData.setPassword(password);
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<String> contacts) {
        this.contacts = contacts;
    }
}
