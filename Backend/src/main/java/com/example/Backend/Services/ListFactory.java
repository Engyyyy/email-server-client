package com.example.Backend.Services;

import com.example.Backend.Model.EmailsList;
import com.example.Backend.Model.UsersList;

public class ListFactory {
    UsersList usersList;

    public ListFactory() {
        usersList = UsersList.getInstance();
    }

    public EmailsList getList(String listName, Integer userId){
        EmailsList list;
        switch (listName) {
            case "draft":
                return usersList.getUser(userId).getDraft();
            case "sentEmails":
                return usersList.getUser(userId).getSentEmails();
            case "receivedEmails":
                return usersList.getUser(userId).getReceivedEmails();
            case "allEmails":
                return usersList.getUser(userId).getAllEmails();
            case "trash":
                return usersList.getUser(userId).getTrash();
            default:
                return null;
        }
    }
}
