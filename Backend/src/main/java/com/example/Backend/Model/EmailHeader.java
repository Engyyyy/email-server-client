package com.example.Backend.Model;

import com.example.Backend.FileManipulation.FileManipulation;

import java.util.UUID;

public class EmailHeader {
    private UUID id;
    private long timeStamp;
    private String senderEmailAddress;
    private String[] receiversEmailAddresses;
    private String subject;
    private int priority;



    public EmailHeader(String senderEmailAddress, String[] receiversEmailAddresses, String subject, int priority) {
        this.timeStamp = System.currentTimeMillis();
        this.id = FileManipulation.generateId();
        this.senderEmailAddress = senderEmailAddress;
        this.receiversEmailAddresses = receiversEmailAddresses;
        this.subject = subject;
        this.priority = priority;
    }

    public EmailHeader() {
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setSenderEmailAddress(String senderEmailAddress) {
        this.senderEmailAddress = senderEmailAddress;
    }

    public void setReceiversEmailAddresses(String[] receiversEmailAddresses) {
        this.receiversEmailAddresses = receiversEmailAddresses;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSenderEmailAddress() {
        return senderEmailAddress;
    }

    public String[] getReceiversEmailAddresses() {
        return receiversEmailAddresses;
    }

    public String getSubject() {
        return subject;
    }


    public int getPriority() {
        return priority;
    }
}
