package com.example.Backend.Model;

import java.sql.Timestamp;

public class EmailHeader {
    private Integer id;
    private User sender;
    private UsersList receivers;
    private String subject;
    private Timestamp timestamp;
    private boolean priority;

    public User getSender() {
        return sender;
    }
    public UsersList getReceivers() {
        return receivers;
    }
    public String getSubject() {
        return subject;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public int getId() {
        return id;
    }
    boolean isPriority() {
        return priority;
    }
}
