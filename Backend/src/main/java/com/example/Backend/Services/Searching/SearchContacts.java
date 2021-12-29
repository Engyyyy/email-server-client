package com.example.Backend.Services.Searching;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Users.User;
import com.example.Backend.Model.UsersList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Component
public class SearchContacts {

    protected User getUser(String emailAddress) {
        return UsersList.listOfUsers.get(emailAddress);
    }

    protected ArrayList<Contact> searchByName(String Name, String emailAddress) {
        ArrayList<Contact> res = new ArrayList<>();
        for (HashMap.Entry<UUID, Contact> contactEntry : getUser(emailAddress).getContacts().entrySet()) {
            if (contactEntry.getValue().getName().equals(Name)) {
                res.add(contactEntry.getValue());
            }
        }
        return res;
    }

    protected ArrayList<Contact> searchByEmailAddress(String userEmailAddress, String emailAddressToBeSearched) {
        ArrayList<Contact> res = new ArrayList<>();
        for (HashMap.Entry<UUID, Contact> contactEntry : getUser(userEmailAddress).getContacts().entrySet()) {
            for (String i : contactEntry.getValue().getEmailAddress()) {
                if (i.equals(emailAddressToBeSearched)) {
                    res.add(contactEntry.getValue());
                }
            }
        }
        return res;
    }


}
