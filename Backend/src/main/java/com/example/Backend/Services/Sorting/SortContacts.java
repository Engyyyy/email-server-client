package com.example.Backend.Services.Sorting;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Services.Rearrangments.Arrange;
import org.springframework.stereotype.Component;

@Component
public class SortContacts {

    public Contact[] sort(String emailAddress, String sortBy) throws Exception {
        switch (sortBy) {
            case "Name":
                return Arrange.sortByName(emailAddress);
            case "NimberOfEmails":
                return Arrange.sortByNumberOfEmails(emailAddress);
            default:
                throw new Exception();
        }
    }

}
