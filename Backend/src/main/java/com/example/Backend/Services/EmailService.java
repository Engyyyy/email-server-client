package com.example.Backend.Services;

import com.example.Backend.Factories.ListFactory;
import com.example.Backend.Model.Email;
import com.example.Backend.Model.EmailBody;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Utility.Utility;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class EmailService {
    //ListFactory listFactory = new ListFactory();

    public Email createEmail(String sender, String[] receivers, String subject, String message, String priority, MultipartFile[] files) throws IOException {
        return new Email(sender, receivers, subject, message, priority, files);
    }


    public void sendEmail(Email email) throws IOException {
        UsersList usersList = new UsersList();
        if (Utility.validateUser(email.getHeader().getSenderEmailAddress())) {
            User sender = UsersList.listOfUsers.get(email.getHeader().getSenderEmailAddress());
            sender.getAllEmails().put(email.getHeader().getId(), email);
            sender.getSentEmails().put(email.getHeader().getId(), email);
           // sender.getAllEmails().get(email.getHeader().getId()).getBody().
            Utility.update(email.getHeader().getSenderEmailAddress(), "allEmails");
            Utility.update(email.getHeader().getSenderEmailAddress(), "sentEmails");
            String[] receiversEmailAddresses = email.getHeader().getReceiversEmailAddresses();
            for (int i = 0; i < receiversEmailAddresses.length; i++) {
                User receiver = usersList.getUser(receiversEmailAddresses[i]);
                if (Utility.validateUser(receiver.getEmailAddress())) {
                    receiver.getAllEmails().put(email.getHeader().getId(), email);
                    receiver.getReceivedEmails().put(email.getHeader().getId(), email);
                    Utility.update(email.getHeader().getReceiversEmailAddresses()[i], "allEmails");
                    Utility.update(email.getHeader().getReceiversEmailAddresses()[i], "receivedEmails");
                }
            }
        }
    }


//    public void deleteEmail(int id, String listName, String emailAdress) {
//        ArrayList<Email> list = listFactory.getList(listName, emailAdress);
//        if (!listName.equals("trash")) {
//            usersList.getListOfUsers().get(emailAdress).getTrash().add(list.get(id));
//        }
//        list.remove(id);
//    }


}
