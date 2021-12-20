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

//    public EmailBody(String content, Attachment[] attachments){
//        this.content = content;
//        this.attachments = attachments;
//    }
}
