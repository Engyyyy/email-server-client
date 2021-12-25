package com.example.Backend.Model;

import java.util.HashMap;
import java.util.UUID;

public class EmailsList {
    private HashMap<UUID, Email> listOfEmails;
    public EmailsList() {
        listOfEmails = new HashMap<>();
    }

    public void addEmail(Email email) {
        listOfEmails.put(email.getHeader().getId(), email);
    }
    public Email getEmail(UUID id) {
        return listOfEmails.get(id);
    }
    public void deleteEmail(UUID Id){
        listOfEmails.remove(Id);
    }
}
