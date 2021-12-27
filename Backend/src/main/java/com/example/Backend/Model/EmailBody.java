package com.example.Backend.Model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class EmailBody {
    String content;
    Attachment[] attachments;

    public EmailBody(MultipartFile[] files, String content) throws IOException {
        this.content = content;
        attachments = new Attachment[files.length];
        for(int i = 0; i < files.length; i++) {
            Attachment attachment = new Attachment(files[i]);
            attachments[i] = attachment;
        }
    }
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
