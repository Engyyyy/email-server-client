package com.example.Backend.Services;

import com.example.Backend.Factories.ListFactory;
import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.FileManipulation.FileManipulation;
import com.example.Backend.Rearrangments.Arrange;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public class EmailService {
    ListFactory listFactory = new ListFactory();

    public Email createEmail(String sender, String[] receivers, String subject, String message, int priority, MultipartFile[] files) throws IOException {
        return new Email(sender, receivers, subject, message, priority, files);
    }


    public void sendEmail(Email email) throws Exception {
        UsersList usersList = new UsersList();
        if (FileManipulation.validateUser(email.getHeader().getSenderEmailAddress())) {
            User sender = UsersList.listOfUsers.get(email.getHeader().getSenderEmailAddress());
            sender.getAllEmails().put(email.getHeader().getId(), email);
            sender.getSentEmails().put(email.getHeader().getId(), email);
//            UUID id1 = email.getHeader().getId();
//            System.out.println(email.getHeader().getSubject());
           // sender.getAllEmails().get(email.getHeader().getId()).getBody().
            FileManipulation.update(email.getHeader().getSenderEmailAddress(), "allEmails");
            FileManipulation.update(email.getHeader().getSenderEmailAddress(), "sentEmails");
            String[] receiversEmailAddresses = email.getHeader().getReceiversEmailAddresses();
            for (int i = 0; i < receiversEmailAddresses.length; i++) {
                User receiver = usersList.getUser(receiversEmailAddresses[i]);
                if (FileManipulation.validateUser(receiver.getEmailAddress())) {
                    receiver.getAllEmails().put(email.getHeader().getId(), email);
                    receiver.getReceivedEmails().put(email.getHeader().getId(), email);
                    FileManipulation.update(email.getHeader().getReceiversEmailAddresses()[i], "allEmails");
                    FileManipulation.update(email.getHeader().getReceiversEmailAddresses()[i], "receivedEmails");
                }
            }
        }
    }

    public Email[] sortEmails(String emailAddress, String list, String criteria) throws Exception {
        if(!FileManipulation.validateUser(emailAddress)) {
            throw new Exception();
        }
        if(criteria.equals("timestamp")) {
            return Arrange.sortByTimestamp(listFactory.getList(list, emailAddress));
        }
        else if(criteria.equals("priority")) {
            return Arrange.sortByPriority(listFactory.getList(list, emailAddress));
        }
        else {
            return new Email[0];
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
