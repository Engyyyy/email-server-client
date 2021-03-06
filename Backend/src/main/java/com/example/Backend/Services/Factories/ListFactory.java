package com.example.Backend.Services.Factories;

import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.UsersList;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class ListFactory implements ListFactoryI {


    public HashMap<UUID, Email> getList(String listName, String emailAdress) throws Exception {
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
                if (UsersList.listOfUsers.get(emailAdress).getOtherFiles().containsKey(listName)) {
                    return UsersList.listOfUsers.get(emailAdress).getOtherFiles().get(listName);
                } else {
                    throw new Exception();
                }
        }
    }

    public void setList(String listName, String emailAdress, HashMap<UUID, Email> list) throws Exception {
        switch (listName) {
            case "draft":
                UsersList.listOfUsers.get(emailAdress).setDraft(list);
                break;
            case "sentEmails":
                UsersList.listOfUsers.get(emailAdress).setSentEmails(list);
                break;
            case "receivedEmails":
                UsersList.listOfUsers.get(emailAdress).setReceivedEmails(list);
                break;
            case "allEmails":
                UsersList.listOfUsers.get(emailAdress).setAllEmails(list);
                break;
            case "trash":
                UsersList.listOfUsers.get(emailAdress).setTrash(list);
                break;
            default:
                if (UsersList.listOfUsers.get(emailAdress).getOtherFiles().containsKey(listName)) {
                    UsersList.listOfUsers.get(emailAdress).setList(listName, list);
                } else {
                    throw new Exception();
                }
        }
    }
}
