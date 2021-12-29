package com.example.Backend.Services.Filter;

import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Services.Factories.ListFactory;
import com.example.Backend.Services.FileManipulation.FileManipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.UUID;

@Component
public class Filter {

    @Autowired
    ListFactory listFactory;

    private void senderMeetCriteria(String emailAddress, String sender) throws Exception {
        for (HashMap.Entry<UUID, Email> emailEntry : UsersList.listOfUsers.get(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getSenderEmailAddress().equals(sender)) {
                listFactory.getList(sender, emailAddress).put(emailEntry.getKey(), emailEntry.getValue());
            }
        }
        FileManipulation.update(emailAddress, sender);
    }

    private void subjectMeetCriteria(String emailAddress, String subject) throws Exception {
        for (HashMap.Entry<UUID, Email> emailEntry : UsersList.listOfUsers.get(emailAddress).getAllEmails().entrySet()) {
            if (emailEntry.getValue().getHeader().getSubject().equals(subject)) {
                listFactory.getList(subject, emailAddress).put(emailEntry.getKey(), emailEntry.getValue());
            }
        }
        FileManipulation.update(emailAddress, subject);
    }

    public void filter(String emailAddress, String fileName, String criteria) throws Exception {
        try {
            FileManipulation.createFile(emailAddress, fileName);
        } catch (Exception e) {
            System.out.println("file name already exists");
        }
        UsersList.listOfUsers.get(emailAddress).getFilterFolders().add(fileName);
        FileManipulation.update(emailAddress,"filterFolders");
        if (criteria.equals("subject")) {
            subjectMeetCriteria(emailAddress, fileName);
        } else if (criteria.equals("sender")) {
            senderMeetCriteria(emailAddress, fileName);
        } else {
            throw new Exception();
        }
    }

    public void checkFilter(String emailAddress, Email email) throws Exception {
        for(String filterCriteria : UsersList.listOfUsers.get(emailAddress).getFilterFolders()){
            if(email.getHeader().getSubject().equals(filterCriteria) || email.getHeader().getSenderEmailAddress().equals(filterCriteria)){
                listFactory.getList(filterCriteria,emailAddress).put(email.getHeader().getId(),email);
                FileManipulation.update(emailAddress,filterCriteria);
            }
        }
    }
}
