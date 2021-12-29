package com.example.Backend.Model.Contacts;

import java.util.ArrayList;
import java.util.UUID;

public class Contact {
    private UUID id;
    private String name;
    private ArrayList<String> emailAddress;

    public Contact(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.emailAddress = new ArrayList<>();
    }

    public Contact() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public ArrayList<String> getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(ArrayList<String> emailAddress) {
        this.emailAddress = emailAddress;
    }
}
