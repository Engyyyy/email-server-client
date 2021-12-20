package com.example.Backend.Services;

import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;

public class EmailService {
    UsersList usersList;
    public EmailService() {
        usersList = UsersList.getInstance();
    }
    public void sendEmail(Email email) {
        User sender = usersList.getUser(email.getHeader().getSenderId());
        sender.getAllEmails().addEmail(email);
        sender.getSentEmails().addEmail(email);

        Integer[] receiversIds = email.getHeader().getReceivers();
        for(int i = 0; i < receiversIds.length; i++) {
            User receiver = usersList.getUser(receiversIds[i]);
            receiver.getAllEmails().addEmail(email);
            receiver.getReceivedEmails().addEmail(email);
        }
    }
}
