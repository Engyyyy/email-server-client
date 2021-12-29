package com.example.Backend.Services.Rearrangments;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.UsersList;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;


public class Arrange {

    public static Email[] sortByPriority(HashMap<UUID, Email> emailsList) {
        PriorityQueue<IntegerEmailEntry> emailsQueue = new PriorityQueue<>();
        for (Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new IntegerEmailEntry(email.getValue().getHeader().getPriority(), email.getValue()));
        }
        return addIntegerPriorityToArrayEmail(emailsQueue);
    }

    public static Email[] sortByNumberOfAttachments(HashMap<UUID, Email> emailsList) {
        PriorityQueue<IntegerEmailEntry> emailsQueue = new PriorityQueue<>();
        for (Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new IntegerEmailEntry(email.getValue().getBody().getAttachments().length, email.getValue()));
        }
        return addIntegerPriorityToArrayEmail(emailsQueue);
    }

    public static Email[] sortByNumberOfReceivers(HashMap<UUID, Email> emailsList) {
        PriorityQueue<IntegerEmailEntry> emailsQueue = new PriorityQueue<>();
        for (Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new IntegerEmailEntry(email.getValue().getHeader().getReceiversEmailAddresses().length, email.getValue()));
        }
        return addIntegerPriorityToArrayEmail(emailsQueue);
    }

    public static Email[] sortBySender(HashMap<UUID, Email> emailsList) {
        PriorityQueue<StringEmailEntry> emailsQueue = new PriorityQueue<>();
        for (Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new StringEmailEntry(email.getValue().getHeader().getSenderEmailAddress(), email.getValue()));
        }
        return addStringPrioritytoArray(emailsQueue);
    }

    public static Email[] sortByContent(HashMap<UUID, Email> emailsList) {
        PriorityQueue<StringEmailEntry> emailsQueue = new PriorityQueue<>();
        for (Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new StringEmailEntry(email.getValue().getBody().getContent(), email.getValue()));
        }
        return addStringPrioritytoArray(emailsQueue);
    }

    public static Email[] sortBySubject(HashMap<UUID, Email> emailsList) {
        PriorityQueue<StringEmailEntry> emailsQueue = new PriorityQueue<>();
        for (Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new StringEmailEntry(email.getValue().getHeader().getSubject(), email.getValue()));
        }
        return addStringPrioritytoArray(emailsQueue);
    }

    private static Email[] addStringPrioritytoArray(PriorityQueue<StringEmailEntry> emailsQueue) {
        Email[] emails = new Email[emailsQueue.size()];
        int counter = 0;
        while (!emailsQueue.isEmpty()) {
            emails[counter++] = emailsQueue.remove().getValue();
        }
        return emails;
    }

    public static Email[] sortByTimestamp(HashMap<UUID, Email> emailsList) {
        PriorityQueue<TimestampEntry> emailsQueue = new PriorityQueue<>();
        for (Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new TimestampEntry(email.getValue().getHeader().getTimeStamp(), email.getValue()));
        }
        return addTimestampToArray(emailsQueue);
    }

    private static Email[] addIntegerPriorityToArrayEmail(PriorityQueue<IntegerEmailEntry> emailsQueue) {
        Email[] emails = new Email[emailsQueue.size()];
        int counter = 0;
        while (!emailsQueue.isEmpty()) {
            emails[counter++] = emailsQueue.remove().getValue();
        }
        return emails;
    }

    private static Contact[] addIntegerPriorityToArrayContact(PriorityQueue<IntegerContactEntry> contactsQueue) {
        Contact[] contacts = new Contact[contactsQueue.size()];
        int counter = 0;
        while (!contactsQueue.isEmpty()) {
            contacts[counter++] = contactsQueue.remove().getValue();
        }
        return contacts;
    }

    private static Email[] addTimestampToArray(PriorityQueue<TimestampEntry> emailsQueue) {
        Email[] emails = new Email[emailsQueue.size()];
        int counter = 0;
        while (!emailsQueue.isEmpty()) {
            emails[counter++] = emailsQueue.remove().getValue();
        }
        return emails;
    }

    public static Contact[] sortByNumberOfEmails(String emailAddress) {
        PriorityQueue<IntegerContactEntry> contactsQ = new PriorityQueue<>();
        for (Map.Entry<UUID, Contact> contactEntry : UsersList.listOfUsers.get(emailAddress).getContacts().entrySet()) {
            contactsQ.add(new IntegerContactEntry(contactEntry.getValue().getEmailAddress().size(), contactEntry.getValue()));
        }
        return addIntegerPriorityToArrayContact(contactsQ);
    }

    private static Contact[] addStringPriorityContact(PriorityQueue<StringContactEntry> contactsQueue) {
        Contact[] contacts = new Contact[contactsQueue.size()];
        int counter = 0;
        while (!contactsQueue.isEmpty()) {
            contacts[counter++] = contactsQueue.remove().getValue();
        }
        return contacts;
    }

    public static Contact[] sortByName(String emailAddress) {
        PriorityQueue<StringContactEntry> contactsQ = new PriorityQueue<>();
        for (Map.Entry<UUID, Contact> contactEntry : UsersList.listOfUsers.get(emailAddress).getContacts().entrySet()) {
            contactsQ.add(new StringContactEntry(contactEntry.getValue().getName(), contactEntry.getValue()));
        }
        return addStringPriorityContact(contactsQ);
    }


}
