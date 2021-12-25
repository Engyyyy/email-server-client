package com.example.Backend.Model;

import java.util.HashMap;

public class EmailsList {
    private HashMap<Integer, Email> listOfEmails;
    public EmailsList() {
        listOfEmails = new HashMap<>();
    }

    public void addEmail(Email email) {
        listOfEmails.put(email.getHeader().getId(), email);
    }
    public Email getEmail(Integer id) {
        return listOfEmails.get(id);
    }
    public void deleteEmail(int Id){
        listOfEmails.remove(Id);
    }
}
