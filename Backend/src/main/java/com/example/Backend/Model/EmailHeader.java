package com.example.Backend.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class EmailHeader {
    private UUID id;
    private String senderEmailAddress;
    private String[] receiversEmailAddresses;
    private String subject;
    private Timestamp timestamp;
    private boolean priority;

//    public EmailHeader(Integer id, Integer senderId, Integer[] receiversIds, String subj, boolean priority) {
//        timestamp = new Timestamp(System.currentTimeMillis());
//        this.id = id;
//        this.senderId = senderId;
//        this.receiversIds = receiversIds;
//        this.subject = subj;
//        this.priority = priority;
//    }

    public UUID getId() {
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    boolean isPriority() {
        return priority;
    }
}
