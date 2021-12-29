package com.example.Backend.Services;


import com.example.Backend.Model.UsersList;

import com.example.Backend.ResponseObjects.ResponseEmail;
import com.example.Backend.Services.Factories.ListFactory;
import com.example.Backend.Model.*;
import com.example.Backend.Services.FileManipulation.FileManipulation;
import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.Users.User;
import com.example.Backend.Services.Rearrangments.Arrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import static java.lang.Math.min;
import java.util.*;

@Component
public class EmailService {
    //ListFactory listFactory = new ListFactory();

    @Autowired
    ListFactory listFactory;

    public Email createEmail(String sender, String[] receivers, String subject, String message, int priority, MultipartFile[] files) throws IOException {
        return new Email(sender, receivers, subject, message, priority, files);
    }

    public void moveEmails(String emailAddress, String from, String to, UUID[] selectedEmails) throws Exception {
        try {
            FileManipulation.fillFile(selectedEmails, emailAddress, to, from);
            FileManipulation.removeFromFile(selectedEmails, emailAddress, from);
            FileManipulation.update(emailAddress, from);
            FileManipulation.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void copyEmails(String emailAddress, String to, String from, UUID[] selectedEmails) throws Exception {
        try {
            FileManipulation.fillFile(selectedEmails, emailAddress, to, from);
            FileManipulation.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void bulkDeletion(String emailAddress, String listName, UUID[] emailsId) throws Exception {
        for (UUID i : emailsId) {
            deleteEmail(emailAddress, listName, i);
        }
    }

    public void bulkRetrieval(String emailAddress, UUID[] emailsId) throws Exception {
        for (UUID i : emailsId) {
            retrieveEmail(emailAddress, i);
        }
    }

    private void deleteEmail(String emailAddress, String listName, UUID emailId) throws Exception {
        if (listName.equals("trash")) {
            getUser(emailAddress).getTrash().remove(emailId);
        } else if (!listName.equals("userBasicData") && FileManipulation.checkBasicFiles(listName)) {
            getUser(emailAddress).getAllEmails().get(emailId).getTrashProperties().add(new TrashProperties("allEmails"));
            getUser(emailAddress).getTrash().put(emailId, getUser(emailAddress).getAllEmails().get(emailId));
            getUser(emailAddress).getAllEmails().remove(emailId);
            loopAndDeleteOnOtherEmails(emailAddress, emailId);
            if (listName.equals("allEmails")) {
                loopAndDeleteOnBasicEmails(emailAddress, emailId);
                FileManipulation.update(emailAddress, "allEmails");
            } else {
                listFactory.getList(listName, emailAddress).remove(emailId);
                FileManipulation.update(emailAddress, "allEmails");
                FileManipulation.update(emailAddress, listName);
            }
        } else {
            listFactory.getList(listName, emailAddress).get(emailId).getTrashProperties().add(new TrashProperties(listName));
            getUser(emailAddress).getTrash().put(emailId, getUser(emailAddress).getAllEmails().get(emailId));
            listFactory.getList(listName, emailAddress).remove(emailId);
            FileManipulation.update(emailAddress, listName);
        }
        FileManipulation.update(emailAddress, "trash");
    }

    private void loopAndDeleteOnBasicEmails(String emailAddress, UUID emailId) throws Exception {
        String[] attributes = {"draft", "receivedEmails", "sentEmails"};
        for (String i : attributes) {
            if (listFactory.getList(i, emailAddress).containsKey(emailId)) {
                listFactory.getList(i, emailAddress).remove(emailId);
                getUser(emailAddress).getTrash().get(emailId).getTrashProperties().add(new TrashProperties(i));
                FileManipulation.update(emailAddress, i);
            }

        }
    }

    private void loopAndDeleteOnOtherEmails(String emailAddress, UUID emailId) throws Exception {
        for (String listName : getUser(emailAddress).getOtherFiles().keySet()) {
            if (listFactory.getList(listName, emailAddress).containsKey(emailId)) {
                listFactory.getList(listName, emailAddress).remove(emailId);
                getUser(emailAddress).getTrash().get(emailId).getTrashProperties().add(new TrashProperties(listName));
                FileManipulation.update(emailAddress, listName);
            }
        }
    }

    private void retrieveEmail(String emailAddress, UUID emailId) throws Exception {
        Email email = getUser(emailAddress).getTrash().get(emailId);
        for (TrashProperties i : email.getTrashProperties()) {
            String initalList = i.getInitialList();
            listFactory.getList(initalList, emailAddress).put(emailId, email);
            if (initalList != "allEmails") {
                getUser(emailAddress).getAllEmails().put(emailId, email);
                FileManipulation.update(emailAddress, "allEmails");
            }
            listFactory.getList(initalList, emailAddress).get(emailId).setTrashProperties(new ArrayList<>());
            getUser(emailAddress).getTrash().remove(emailId);
            FileManipulation.update(emailAddress, "trash");
            FileManipulation.update(emailAddress, initalList);
        }
    }

    private boolean check(Date currentDate, Date prevDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentDate.getTime());
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTimeInMillis(prevDate.getTime());
        int prevYear = calendar.get(Calendar.YEAR);
        int prevMonth = calendar.get(Calendar.MONTH);
        int prevDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (currentYear == prevYear) {
            if (currentMonth == prevMonth) {
                if (currentDay == prevDay) {
                    return false;
                } else {
                    return currentDay - prevDay >= 30;
                }
            } else {
                if (currentMonth - prevMonth == 1) {
                    return (currentDay + (30 - prevDay)) >= 30;
                } else return false;
            }
        } else {
            if (currentMonth == 1 && prevMonth == 12) {
                return currentDay - prevDay >= 30;
            } else return false;
        }
    }

    public void checkTrash(String emailAddress) throws Exception {
        Date currentDate = new Date(System.currentTimeMillis());
        for (HashMap.Entry<UUID, Email> email : getUser(emailAddress).getTrash().entrySet()) {
            UUID emailID = email.getKey();
            int j = 0;
            while (j < email.getValue().getTrashProperties().size()) {
                Date prevDate = email.getValue().getTrashProperties().get(j).getStartDate();
                if (check(currentDate, prevDate)) {
                    if (email.getValue().getTrashProperties().size() == 1) {
                        getUser(emailAddress).getTrash().remove(emailID);
                    } else {
                        email.getValue().getTrashProperties().remove(j);
                        j--;
                    }
                }
                j++;
            }
        }
        FileManipulation.update(emailAddress, "trash");

    }


    public int getIntegerPriority(String priority) throws Exception {
        switch (priority) {
            case "very high":
                return 1;
            case "high":
                return 2;
            case "medium":
                return 3;
            case "low":
                return 4;
            default:
                throw new Exception();
        }
    }

    private User getUser(String emailAddress) {
        return UsersList.listOfUsers.get(emailAddress);
    }

    public void saveToDraft(Email email) throws Exception {
        String emailAddress = email.getHeader().getSenderEmailAddress();
        getUser(emailAddress).getDraft().put(email.getHeader().getId(), email);
        getUser(emailAddress).getAllEmails().put(email.getHeader().getId(), email);
        FileManipulation.update(emailAddress, "draft");
        FileManipulation.update(emailAddress, "allEmails");
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

    private Email[] sortEmails(String emailAddress, String list, String criteria) throws Exception {
        if(!FileManipulation.validateUser(emailAddress)) {
            System.out.println("User Not Found");
            throw new Exception();
        }
        if(criteria.equals("timestamp")) {
            return Arrange.sortByTimestamp(listFactory.getList(list, emailAddress));
        }
        else if(criteria.equals("priority")) {
            return Arrange.sortByPriority(listFactory.getList(list, emailAddress));
        }
        else {
            System.out.println("No Such Sorting Criteria");
            return new Email[0];
        }
    }

    public ArrayList<ResponseEmail> getEmails(String emailAddress, String list, String criteria, int pageNumber, int itemsPerPage) throws Exception {
        Email[] emails = sortEmails(emailAddress, list, criteria);
        ArrayList<ResponseEmail> requiredEmails = new ArrayList<>();
        int start = ((pageNumber-1)*itemsPerPage);
        System.out.println(itemsPerPage);
        for(int i = start; i < min(emails.length, start + itemsPerPage); i++) {
            UUID id = emails[i].getHeader().getId();
            String senders = emails[i].getHeader().getSenderEmailAddress();
            String[] receivers = emails[i].getHeader().getReceiversEmailAddresses();
            long timestamp = emails[i].getHeader().getTimeStamp();
            String subject = emails[i].getHeader().getSubject();
            String content = emails[i].getBody().getContent();
            ResponseEmail responseEmail = new ResponseEmail(id, senders, receivers, timestamp, subject, content);
            requiredEmails.add(responseEmail);
            System.out.println(i);
        }
        return requiredEmails;
    }

    public int getEmailsLength(String emailAddress, String list) throws Exception {
        return listFactory.getList(list, emailAddress).keySet().size();
    }


//    public void deleteEmail(int id, String listName, String emailAdress) {
//        ArrayList<Email> list = listFactory.getList(listName, emailAdress);
//        if (!listName.equals("trash")) {
//            usersList.getListOfUsers().get(emailAdress).getTrash().add(list.get(id));
//        }
//        list.remove(id);
//    }


}
