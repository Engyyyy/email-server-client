package com.example.Backend.Model.Email;

import com.example.Backend.Model.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class EmailBody {
    String content;
    Attachment[] attachments;

    public EmailBody(MultipartFile[] files, String content) throws IOException {
        this.content = content;
        if(files == null) {
            attachments = new Attachment[0];
        }
        else attachments = new Attachment[files.length];
        for(int i = 0; i < attachments.length; i++) {
            Attachment attachment = new Attachment(files[i]);
            attachments[i] = attachment;
        }
    }
    public String getContent() {
        return content;
    }

    public Attachment[] getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachment[] attachments) {
        this.attachments = attachments;
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
