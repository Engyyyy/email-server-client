package com.example.Backend.Services.Searching;

import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.Users.User;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component
public class SearchEmails {

    @Autowired
    EmailService emailService;

    private User getUser(String emailAddress) {
        return UsersList.listOfUsers.get(emailAddress);
    }

    protected ArrayList<Email> searchByDate(String stDate, String emailAddress) throws ParseException {
        int[] date = parseDate(stDate);
        int day = date[0], month = date[1], year = date[2];
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

    protected int[] parseDate(String stDate) throws ParseException {
        int[] date = new int[3];
        LocalDate currentDate = LocalDate.parse(stDate);
        date[0] = currentDate.getDayOfMonth();
        date[1] = currentDate.getMonthValue();
        date[2] = currentDate.getYear();
        return date;
    }


    protected ArrayList<Email> searchBySender(String senderEmailAddress, String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getSenderEmailAddress().equals(senderEmailAddress)) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    protected ArrayList<Email> searchByReceiver(String receiverEmailAddress, String emailAddress) {
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

    protected ArrayList<Email> searchBySubject(String subject, String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getSubject().equals(subject)) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    protected ArrayList<Email> searchByContent(String sentence, String emailAddress) {
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getBody().getContent().contains(sentence)) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    protected ArrayList<Email> searchByPriority(String stringPriority, String emailAddress) throws Exception {
        int priority = emailService.getIntegerPriority(stringPriority);
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getPriority() == priority) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }

    protected ArrayList<Email> searchByAttachment(String stNum, String emailAddress) {
        int num = Integer.parseInt(stNum);
        ArrayList<Email> result = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : getUser(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getBody().getAttachments().length == num) {
                result.add(emailEntry.getValue());
            }
        }
        return result;
    }


}
