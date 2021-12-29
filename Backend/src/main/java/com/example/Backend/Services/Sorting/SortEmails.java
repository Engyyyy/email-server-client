package com.example.Backend.Services.Sorting;

import com.example.Backend.Model.Email.Email;
import com.example.Backend.Services.Factories.ListFactory;
import com.example.Backend.Services.Rearrangments.Arrange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SortEmails {

    @Autowired
    ListFactory listFactory;

    public Email[] sortEmail(String listName, String emailAddress, String sortBy) throws Exception {
        switch (sortBy) {
            case "Date":
                return Arrange.sortByTimestamp(listFactory.getList(emailAddress, listName));
            case "Priority":
                return Arrange.sortByPriority(listFactory.getList(emailAddress, listName));
            case "AttachmentsNumber":
                return Arrange.sortByNumberOfAttachments(listFactory.getList(emailAddress, listName));
            case "Sender":
                return Arrange.sortBySender(listFactory.getList(listName, emailAddress));
            case "Content":
                return Arrange.sortByContent(listFactory.getList(listName, emailAddress));
            case "Subject":
                return Arrange.sortBySubject(listFactory.getList(listName, emailAddress));
            case "NumberOfReceivers":
                return Arrange.sortByNumberOfReceivers(listFactory.getList(listName, emailAddress));
            default:
                throw new Exception();
        }
    }


}
