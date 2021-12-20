package com.example.Backend.Model;

import java.util.HashMap;

public class EmailsList {
    private static EmailsList emailsList;
    HashMap<Integer, Email> listOfEmails;

    private EmailsList() {

    }
    public EmailsList getInstance() {
        return emailsList;
    }
}
