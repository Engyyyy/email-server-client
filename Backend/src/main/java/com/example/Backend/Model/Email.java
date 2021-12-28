package com.example.Backend.Model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public class Email {
    private EmailHeader header;
    private EmailBody body;
    private ArrayList<TrashProperties> trashProperties ;

    public Email() {
        this.trashProperties = new ArrayList<>();
    }
    public Email(String sender, String[] receivers, String subject, String message, String priority, MultipartFile[] files) throws IOException {
        this.body = new EmailBody(files, message);
        this.header = new EmailHeader(sender, receivers, subject, priority);
        this.trashProperties = new ArrayList<>();
    }

    public ArrayList<TrashProperties> getTrashProperties() {
        return this.trashProperties;
    }

    public void setTrashProperties(ArrayList<TrashProperties> trashProperties) {
        this.trashProperties = trashProperties;
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
        this.trashProperties = new ArrayList<>();

    }



}
