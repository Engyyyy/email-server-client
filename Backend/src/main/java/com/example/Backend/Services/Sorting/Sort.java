package com.example.Backend.Services.Sorting;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Email.Email;
import com.example.Backend.Services.Searching.SearchEmails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sort {

    @Autowired
    SortContacts sortContacts;
    @Autowired
    SortEmails sortEmails;

    public Contact[] sort(String emailAddress, String sortBy) throws Exception {
        return sortContacts.sort(emailAddress, sortBy);
    }

    public Email[] sortEmail(String listName, String emailAddress, String sortBy) throws Exception {
        return sortEmails.sortEmail(listName,emailAddress,sortBy);
    }
}