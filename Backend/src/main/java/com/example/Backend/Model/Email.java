package com.example.Backend.Model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class Email {
    private EmailHeader header;
    private EmailBody body;

    public Email() {
    }
    public Email(String sender, String[] receivers, String subject, String message, String priority, MultipartFile[] files) throws IOException {
        this.body = new EmailBody(files, message);
        this.header = new EmailHeader(sender, receivers, subject, priority);
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
