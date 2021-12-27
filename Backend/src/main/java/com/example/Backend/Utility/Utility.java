package com.example.Backend.Utility;

import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UserBasicData;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Factories.ListFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Utility {

    private static ObjectMapper mapper = new ObjectMapper();
    private static String[] attributes = new String[]{"allEmails", "contacts", "draft", "receivedEmails", "sentEmails", "trash", "userBasicData"};
    private static ListFactory listFactory = new ListFactory();
    private static UserBasicData userBasicData = new UserBasicData();

    public static final boolean validateUser(String emailAddress) {
        for (HashMap.Entry<String, User> user : UsersList.listOfUsers.entrySet()) {
            System.out.println(user.getKey() + "  :  " + user.getValue());
            if (user.getValue().getEmailAddress().equals(emailAddress)) {
                return true;
            }
        }
        return false;
    }


    public static void loadListOfUsers() throws Exception {
        String path = "src/main/resources/DB";
        String subPath;
        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        assert directories != null;
        for (String emailAddress : directories) {
            User user = new User(emailAddress);
            UsersList.listOfUsers.put(emailAddress, user);
            for (String listName : attributes) {
                subPath = path + "/" + emailAddress + "/" + listName + ".json";
                if (listName.equals("userBasicData")) {
                    UserBasicData list;
                    list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    user.setUserBasicData(list);
                } else if (listName.equals("contacts")) {
                    ArrayList<String> list;
                    list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    user.setContacts(list);
                } else {
                    HashMap<UUID, Email> list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    listFactory.setList(listName, emailAddress, list);
                }
            }
        }
    }

    public static void createUserDirectory(String emailAddress) throws Exception {
        String path = "src/main/resources/DB/" + emailAddress;
        HashMap<UUID, Email> emptyMap = new HashMap<>();
        UserBasicData emptyUserBasicData = new UserBasicData();
        ArrayList<Integer> emptyArray = new ArrayList<>();
        Files.createDirectories(Paths.get(path));
        path += "/";
        for (String i : attributes) {
            File myObj = new File(path + i + ".json");
            if (!myObj.createNewFile()) throw new Exception();
            if (i.equals("contacts")) {
                mapper.writeValue(new FileWriter(path + i + ".json"), emptyArray);
            } else if (i.equals("userBasicData")) {
                mapper.writeValue(new FileWriter(path + i + ".json"), emptyUserBasicData);
            } else {
                mapper.writeValue(new FileWriter(path + i + ".json"), emptyMap);
            }

        }
    }

    public static void update(String emailAddress, String listName) throws IOException {
        Object list = listFactory.getList(listName, emailAddress);
        String path = "src/main/resources/DB/" + emailAddress + "/" + listName + ".json";
        mapper.writeValue(new FileWriter(path), list);
    }

    public static UUID generateId() {
        return UUID.randomUUID();
    }
}
