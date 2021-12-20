package com.example.Backend.Model;

import java.io.File;
import java.sql.Timestamp;

public class Email {
    private User sender;
    private User[] receivers;
    private String subject;
    private String content;
    private Timestamp timestamp;
    private boolean priority;
    private File[] attachments;

    User getSender() {
        return sender;
    }
    User[] getReceivers() {
        return receivers;
    }
    String getSubject() {
        return subject;
    }
    String getContent() {
        return content;
    }
    Timestamp getTimestamp() {
        return timestamp;
    }
    boolean isPriority() {
        return priority;
    }
    File[] getAttachments() {
        return attachments;
    }
}
