package com.example.Backend.Services;

import com.example.Backend.Model.UsersList;
import org.springframework.beans.factory.annotation.Autowired;

public class ListFactory {
    @Autowired
    UsersList usersList;
/*
    public ArrayList<Email> getList(String listName, String emailAdress) {
        EmailsList list;
        switch (listName) {
            case "draft":
                return usersList.getListOfUsers().get(emailAdress).getDraft();
            case "sentEmails":
                return usersList.getListOfUsers().get(emailAdress).getSentEmails();
            case "receivedEmails":
                return usersList.getListOfUsers().get(emailAdress).getReceivedEmails();
            case "allEmails":
                return usersList.getListOfUsers().get(emailAdress).getAllEmails();
            case "trash":
                return usersList.getListOfUsers().get(emailAdress).getTrash();
            default:
                return null;
        }
    }*/
}
