package com.example.Backend.Services.Searching;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Search {
    @Autowired
    SearchEmails searchEmails;
    @Autowired
    SearchContacts searchContacts;

    public ArrayList<Contact> searchContacts(String emailAddress, String searchAbout, String stringToBeSearched) throws Exception {
        switch (searchAbout) {
            case "Name":
                return searchContacts.searchByName(stringToBeSearched, emailAddress);
            case "EmailAddress":
                return searchContacts.searchByEmailAddress(emailAddress, stringToBeSearched);
            default:
                throw new Exception();
        }
    }

    public ArrayList<Email> searchEmails(String emailAddress, String searchAbout, String wordToBeSearched) throws Exception {
        switch (searchAbout) {
            case "Date":
                return searchEmails.searchByDate(wordToBeSearched, emailAddress);
            case "Sender":
                return searchEmails.searchBySender(wordToBeSearched, emailAddress);
            case "Receiver":
                return searchEmails.searchByReceiver(wordToBeSearched, emailAddress);
            case "Subject":
                return searchEmails.searchBySubject(wordToBeSearched, emailAddress);
            case "Content":
                return searchEmails.searchByContent(wordToBeSearched, emailAddress);
            case "Priority":
                return searchEmails.searchByPriority(wordToBeSearched, emailAddress);
            case "Attachment":
                return searchEmails.searchByAttachment(wordToBeSearched, emailAddress);
            default:
                throw new Exception();
        }
    }
}
