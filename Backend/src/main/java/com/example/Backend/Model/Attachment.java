package com.example.Backend.Model;

import com.example.Backend.Utility.Utility;
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
    public Attachment(MultipartFile file) throws IOException {
        attachmentId = createFile(file);
        name = file.getOriginalFilename();
    }
    private UUID createFile(MultipartFile file) throws IOException {
        UUID id = Utility.generateId();
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
}
