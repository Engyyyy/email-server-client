package com.example.Backend.Model;

public class Email {
    private EmailHeader header;
    private EmailBody body;

    public EmailHeader getHeader() {
        return header;
    }
    public EmailBody getBody() {
        return body;
    }

//    public Email(EmailHeader header, EmailBody body) {
//        this.header = header;
//        this.body = body;
//    }
}