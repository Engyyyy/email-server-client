package com.example.Backend.Model;

public class EmailBody {
    String content;
    Attachment[] attachments;

    public String getContent() {
        return content;
    }
    public Attachment[] getAttachments() {
        return attachments;
    }
}
