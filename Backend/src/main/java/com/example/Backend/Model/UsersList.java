package com.example.Backend.Model;

import com.example.Backend.Factories.ListFactory;
import com.example.Backend.FileManipulation.FileManipulation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


@Component
public class UsersList {

    @Autowired
    ListFactory listFactory;
    public static HashMap<String, User> listOfUsers = new HashMap<>();

    public UsersList() {

    }

    public void addUser(String emailAddress, String password, String firstNAme, String lastName) throws Exception {
        FileManipulation.createUserDirectory(emailAddress);
        User user = new User(firstNAme, lastName, emailAddress, password);
        String path = "src/main/resources/DB/" + emailAddress + "/" + "userBasicData.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new FileWriter(path), user.getUserBasicData());
        } catch (Exception e) {
            System.out.println(e);
        }
        listOfUsers.put(emailAddress, user);
    }

    public void moveEmails(String emailAddress, String from, String to, UUID[] selectedEmails) throws Exception {
        try {
            FileManipulation.fillFile(selectedEmails, emailAddress, to, from);
            FileManipulation.removeFromFile(selectedEmails, emailAddress, from);
            FileManipulation.update(emailAddress, from);
            FileManipulation.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void copyEmails(String emailAddress, String to, String from, UUID[] selectedEmails) throws Exception {
        try {
            FileManipulation.fillFile(selectedEmails, emailAddress, to, from);
            FileManipulation.update(emailAddress, to);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void bulkDeletion(String emailAddress, String listName, UUID[] emailsId) throws Exception {
        for (UUID i : emailsId) {
            deleteEmail(emailAddress, listName, i);
        }
    }

    public void bulkRetrieval(String emailAddress, UUID[] emailsId) throws Exception {
        for (UUID i : emailsId) {
            retrieveEmail(emailAddress, i);
        }
    }

    private void deleteEmail(String emailAddress, String listName, UUID emailId) throws Exception {
        if (!listName.equals("trash")) {
            TrashProperties trashProperties = new TrashProperties(listName);
            listFactory.getList(listName, emailAddress).get(emailId).getTrashProperties().add(trashProperties);
            getUser(emailAddress).getTrash().put(emailId, listFactory.getList(listName, emailAddress).get(emailId));
            FileManipulation.update(emailAddress, "trash");
        }
        listFactory.getList(listName, emailAddress).remove(emailId);
        FileManipulation.update(emailAddress, listName);
    }

    private void retrieveEmail(String emailAddress, UUID emailId) throws Exception {
        Email email = getUser(emailAddress).getTrash().get(emailId);
        for (TrashProperties i : email.getTrashProperties()) {
            String initalList = i.getInitialList();
            listFactory.getList(initalList, emailAddress).put(emailId, email);
            listFactory.getList(initalList, emailAddress).get(emailId).setTrashProperties(new ArrayList<>());
            getUser(emailAddress).getTrash().remove(emailId);
            FileManipulation.update(emailAddress, "trash");
            FileManipulation.update(emailAddress, initalList);
        }

    }

 /*   public void checkTrash(String emailAddress) {
        long currentTime = System.currentTimeMillis();
        long thirtyDays = 2592000000;
        for (HashMap.Entry<UUID, Email> i : getUser(emailAddress).getTrash().entrySet()) {
            for (TrashProperties j : i.getValue().getTrashProperties()) {
                if ((j.getTimestamp() - currentTime) >= )
            }
        }

    }*/


    public User getUser(String emailAddress) {
        return listOfUsers.get(emailAddress);
    }


}
