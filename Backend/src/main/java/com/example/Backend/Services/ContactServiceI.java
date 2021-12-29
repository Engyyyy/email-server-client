package com.example.Backend.Services;

import com.example.Backend.Model.Contacts.Contact;

import java.util.UUID;

public interface ContactServiceI {
    public Contact createContact(String name, String emailAddress) throws Exception;

    public void addEmailAddressesToContact(String[] emailAddresses, String userEmailAddress, UUID contactId) throws Exception;

    public void deleteContact(String userEmailAddress, UUID contactId) throws Exception;

    public void renameContact(String emailAddress, UUID contactId, String newName) throws Exception;
}
