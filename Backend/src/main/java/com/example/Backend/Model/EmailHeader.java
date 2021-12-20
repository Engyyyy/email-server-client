package com.example.Backend.Model;

import java.sql.Date;
import java.sql.Timestamp;

public class EmailHeader {
    private Integer id;
    private Integer senderId;
    private Integer[] receiversIds;
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

    public Integer getSenderId() {
        return senderId;
    }
    public Integer[] getReceivers() {
        return receiversIds;
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