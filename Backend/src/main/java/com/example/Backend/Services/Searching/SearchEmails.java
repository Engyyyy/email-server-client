package com.example.Backend.Services.Searching;

import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.Users.User;
import com.example.Backend.Model.UsersList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class SearchEmails  {


    private User getUser(String emailAddress) {
        return UsersList.listOfUsers.get(emailAddress);
    }

    public ArrayList<Email> searchByDate(int day, int month, int year,String emailAddress) {
        int emailYear, emailMonth, emailDay;
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(emailEntry.getValue().getHeader().getStartDate().getTime());
            emailYear = calendar.get(Calendar.YEAR);
            emailMonth = calendar.get(Calendar.MONTH);
            emailDay = calendar.get(Calendar.DAY_OF_MONTH);
            if (emailDay == day && emailMonth == month && emailYear == year) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    public ArrayList<Email> searchBySender(String senderEmailAddress,String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getSenderEmailAddress().equals(senderEmailAddress)) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    public ArrayList<Email> searchByReceiver(String receiverEmailAddress,String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            for (String receiver : emailEntry.getValue().getHeader().getReceiversEmailAddresses()) {
                if (receiver.equals(receiverEmailAddress)) {
                    result.add(emailEntry.getValue());
                }
            }
        }
        return result;
    }

    public ArrayList<Email> searchBySubject(String subject,String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getSubject().equals(subject)) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    public ArrayList<Email> searchByContent(String sentence,String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getBody().getContent().contains(sentence)) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    public ArrayList<Email> searchByPriority(int priority,String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getPriority() == priority) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    public ArrayList<Email> searchByAttachment(int num,String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getBody().getAttachments().length == num) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }
}
