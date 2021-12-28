package com.example.Backend.Model;

import com.example.Backend.FileManipulation.FileManipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Attachment {

    @Autowired
    private HttpServletRequest request;

    UUID attachmentId;
    String name;
    public Attachment(){
    }
    public Attachment(MultipartFile file) throws IOException {
        attachmentId = createFile(file);
        name = file.getOriginalFilename();
    }
    private UUID createFile(MultipartFile file) throws IOException {
        UUID id = FileManipulation.generateId();
        String dirPath = "src/main/resources/attachments/"+id;
        File dir = new File(dirPath);
        dir.mkdir();

        File f = new File("./");
        File dest = new File(dirPath+"/"+file.getOriginalFilename());
        dest.createNewFile();
        System.out.println("CREATED");
        file.transferTo(dest.getAbsoluteFile());
        return id;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public UUID getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(UUID attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
