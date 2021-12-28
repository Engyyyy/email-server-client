package com.example.Backend.Rearrangments;

import com.example.Backend.Model.Email;

import java.util.*;

public class Arrange {

    public static Email[] sortByPriority(HashMap<UUID, Email> emailsList) {
        PriorityQueue<PriorityEntry> emailsQueue = new PriorityQueue<>();
        for(Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new PriorityEntry(email.getValue().getHeader().getPriority(), email.getValue()));
        }
        return addPriorityToArray(emailsQueue);
    }

    public static Email[] sortByTimestamp(HashMap<UUID, Email> emailsList) {
        PriorityQueue<TimestampEntry> emailsQueue = new PriorityQueue<>();
        for(Map.Entry<UUID, Email> email : emailsList.entrySet()) {
            emailsQueue.add(new TimestampEntry(email.getValue().getHeader().getTimeStamp(), email.getValue()));
        }
        return addTimestampToArray(emailsQueue);
    }

    private static Email[] addPriorityToArray(PriorityQueue<PriorityEntry> emailsQueue) {
        Email[] emails = new Email[emailsQueue.size()];
        int counter = 0;
        while(!emailsQueue.isEmpty()) {
            emails[counter++] = emailsQueue.remove().getValue();
        }
        return emails;
    }

    private static Email[] addTimestampToArray(PriorityQueue<TimestampEntry> emailsQueue) {
        Email[] emails = new Email[emailsQueue.size()];
        int counter = 0;
        while(!emailsQueue.isEmpty()) {
            emails[counter++] = emailsQueue.remove().getValue();
        }
        return emails;
    }
}
