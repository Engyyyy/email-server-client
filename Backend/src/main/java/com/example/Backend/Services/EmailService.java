package com.example.Backend.Services;

import com.example.Backend.Model.Email;
import com.example.Backend.Model.EmailsList;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;

public class EmailService {
    UsersList usersList;
    ListFactory listFactory = new ListFactory();

    public EmailService() {
        usersList = UsersList.getInstance();
    }

    public void sendEmail(Email email) {
        User sender = usersList.getUser(email.getHeader().getSenderId());
        sender.getAllEmails().addEmail(email);
        sender.getSentEmails().addEmail(email);

        Integer[] receiversIds = email.getHeader().getReceivers();
        for (int i = 0; i < receiversIds.length; i++) {
            User receiver = usersList.getUser(receiversIds[i]);
            receiver.getAllEmails().addEmail(email);
            receiver.getReceivedEmails().addEmail(email);
        }
    }

    public void deleteEmail(Email email, String listName) {
        EmailsList list = listFactory.getList(listName, usersList.currentUser.getId());
        list.deleteEmail(email.getHeader().getId());
        if(!listName.equals("trash")){
            usersList.currentUser.getTrash().addEmail(email);
        }
    }

}
