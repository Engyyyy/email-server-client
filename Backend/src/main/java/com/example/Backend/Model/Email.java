package com.example.Backend.Model;

public class Email {
    private EmailHeader header;
    private EmailBody body;

    public Email() {
    }

    public EmailHeader getHeader() {
        return header;
    }

    public EmailBody getBody() {
        return body;
    }

    public void setHeader(EmailHeader header) {
        this.header = header;
    }

    public void setBody(EmailBody body) {
        this.body = body;
    }

    public Email(EmailHeader header, EmailBody body) {
        this.header = header;
        this.body = body;
    }
}
