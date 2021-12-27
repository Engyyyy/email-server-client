package com.example.Backend.Services;

import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailService {
    ListFactory listFactory = new ListFactory();

/*
    public void sendEmail(Email email) {
        if (Utility.validateUser(email.getHeader().getSenderEmailAddress())) {
            User sender = UsersList.listOfUsers.get(email.getHeader().getSenderEmailAddress());
            sender.getAllEmails().put(email);
            sender.getSentEmails().add(email);
            String[] receiversEmailAddresses = email.getHeader().getReceiversEmailAddresses();
            for (int i = 0; i < receiversEmailAddresses.length; i++) {
                User receiver = usersList.getUser(receiversEmailAddresses[i]);
                if (Utility.validateUser(receiver.getEmailAddress())) {
                    receiver.getAllEmails().add(email);
                    receiver.getReceivedEmails().add(email);
                }
            }
        }
    }
/*

    public void deleteEmail(int id, String listName, String emailAdress) {
        ArrayList<Email> list = listFactory.getList(listName, emailAdress);
        if (!listName.equals("trash")) {
            usersList.getListOfUsers().get(emailAdress).getTrash().add(list.get(id));
        }
        list.remove(id);
    }
*/

}
