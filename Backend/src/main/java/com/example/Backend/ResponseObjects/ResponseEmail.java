package com.example.Backend.ResponseObjects;

import java.util.UUID;

public class ResponseEmail {
    UUID id;
    String sender;
    String[] receivers;
    long timestamp;
    String subject;
    String content;
    public ResponseEmail(UUID id, String sender, String[] receivers, long timestamp, String subject, String content) {
        this.id = id;
        this.sender = sender;
        this.receivers = receivers;
        this.timestamp = timestamp;
        this.subject = subject;
        this.content = content;
    }
    public ResponseEmail() {}

    public UUID getId() { return id; }
    public String getSender() { return sender; }
    public String[] getReceivers() { return receivers; }
    public long getTimestamp() { return timestamp; }
    public String getSubject() { return subject; }
    public String getContent() { return content; }
}
