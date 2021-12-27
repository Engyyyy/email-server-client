package com.example.Backend.Services;

import com.example.Backend.Model.Email;
import com.example.Backend.Model.UsersList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

public class ListFactory {
    @Autowired
    UsersList usersList;

    public HashMap<UUID, Email> getList(String listName, String emailAdress) {
        switch (listName) {
            case "draft":
                return UsersList.listOfUsers.get(emailAdress).getDraft();
            case "sentEmails":
                return UsersList.listOfUsers.get(emailAdress).getSentEmails();
            case "receivedEmails":
                return UsersList.listOfUsers.get(emailAdress).getReceivedEmails();
            case "allEmails":
                return UsersList.listOfUsers.get(emailAdress).getAllEmails();
            case "trash":
                return UsersList.listOfUsers.get(emailAdress).getTrash();
            default:
                return null;
        }
    }
}
