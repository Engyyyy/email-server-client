package com.example.Backend.Services;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Services.FileManipulation.FileManipulation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ContactServices implements ContactServiceI{

    public void createContact(String name, String emailAddress) throws Exception {
        Contact contact = new Contact(name);
        UsersList.listOfUsers.get(emailAddress).getContacts().put(contact.getId(), contact);
        FileManipulation.update(emailAddress, "contacts");
    }

    public ArrayList<Contact> getContacts(String emailAddress) {
        HashMap<UUID, Contact> userContacts = UsersList.listOfUsers.get(emailAddress).getContacts();
        ArrayList<Contact> contacts = new ArrayList<>();
        for(Map.Entry<UUID, Contact> contactEntry : userContacts.entrySet()) {
            contacts.add(contactEntry.getValue());
        }
        return contacts;
    }

    public void addEmailAddressesToContact(String[] emailAddresses, String userEmailAddress, UUID contactId) throws Exception {
        if (!UsersList.listOfUsers.get(userEmailAddress).getContacts().containsKey(contactId)) throw new Exception();
        for (String i : emailAddresses) {
            if (FileManipulation.validateUser(i)) {
                UsersList.listOfUsers.get(userEmailAddress).getContacts().get(contactId).getEmailAddress().add(i);
            } else {
                retrieve(userEmailAddress);
                throw new Exception();
            }
        }
        FileManipulation.update(userEmailAddress, "contacts");
    }

    public void deleteContact(String userEmailAddress, UUID contactId) throws Exception {
        if (!UsersList.listOfUsers.get(userEmailAddress).getContacts().containsKey(contactId)) throw new Exception();
        UsersList.listOfUsers.get(userEmailAddress).getContacts().remove(contactId);
        FileManipulation.update(userEmailAddress, "contacts");
    }

    public void renameContact(String emailAddress, UUID contactId, String newName) throws Exception {
        if (!UsersList.listOfUsers.get(emailAddress).getContacts().containsKey(contactId)) throw new Exception();
        Contact contact = new Contact(newName);
        contact.setEmailAddress(UsersList.listOfUsers.get(emailAddress).getContacts().get(contactId).getEmailAddress());
        contact.setId(contactId);
        UsersList.listOfUsers.get(emailAddress).getContacts().remove(contactId);
        UsersList.listOfUsers.get(emailAddress).getContacts().put(contactId, contact);
        FileManipulation.update(emailAddress, "contacts");
    }

    private void retrieve(String userEmailAddress) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UsersList.listOfUsers.get(userEmailAddress).setContacts(mapper.readValue(new File("src/main/resources/DB/" + userEmailAddress + "/contacts.json"), new TypeReference<>() {
        }));
    }

}
