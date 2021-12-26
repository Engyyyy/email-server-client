package com.example.Backend.Model;

public class EmailBody {
    String content;
    //  Attachment[] attachments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EmailBody() {
    }

    public EmailBody(String content) {
        this.content = content;
    }

}
