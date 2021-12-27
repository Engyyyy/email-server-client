package com.example.Backend.Model;

public class UserBasicData {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String password;

    public UserBasicData(){}
    public UserBasicData(String firstname, String lastname, String emailAddress, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
