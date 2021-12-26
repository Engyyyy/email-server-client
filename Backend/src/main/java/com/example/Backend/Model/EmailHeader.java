package com.example.Backend.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class EmailHeader {
    private Timestamp timeStamp;
    private int id;
    private String senderEmailAddress;
    private String[] receiversEmailAddresses;
    private String subject;
    private boolean priority;

    public void setId(int id) {
        this.id = id;
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

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public EmailHeader( int id, String senderEmailAddress, String[] receiversEmailAddresses, String subject, boolean priority) {
        this.timeStamp = new Timestamp(System.currentTimeMillis());
        this.id = id;
        this.senderEmailAddress = senderEmailAddress;
        this.receiversEmailAddresses = receiversEmailAddresses;
        this.subject = subject;
        this.priority = priority;
    }

    public int getId() {
        return id;
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


    boolean isPriority() {
        return priority;
    }
}
