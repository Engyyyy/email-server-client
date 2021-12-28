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

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String[] attributes = new String[]{"allEmails", "contacts", "draft", "receivedEmails", "sentEmails", "trash", "userBasicData"};
    private static final ListFactory listFactory = new ListFactory();


    public static  boolean validateUser(String emailAddress) {
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
        String[] directories = getAllDirectories();
        assert directories != null;
        for (String emailAddress : directories) {
            User user = new User(emailAddress);
            //add User
            UsersList.listOfUsers.put(emailAddress, user);
            File folder = new File(path + "/" + emailAddress);
            File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File i : listOfFiles) {
                String subPath = i.toString();
                String fileName = i.getName();
                if (fileName.equals("userBasicData.json")) {
                    UserBasicData userBasicData = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    getUser(emailAddress).setUserBasicData(userBasicData);
                } else if (fileName.equals("contacts.json")) {
                    ArrayList<String> contacts = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    getUser(emailAddress).setContacts(contacts);
                } else {
                    fileName= fileName.replaceAll(".json","");
                    HashMap<UUID, Email> list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    if (checkBasicFiles(fileName)) {
                        listFactory.setList(fileName, emailAddress, list);
                    } else {
                        getUser(emailAddress).getOtherFiles().put(fileName, list);
                    }
                }
            }
        }
    }
    
    private static User getUser(String emailAddress){
        return UsersList.listOfUsers.get(emailAddress);
    }
    
    private static boolean checkBasicFiles(String fileName) {
        for (String i : attributes) {
            if (i.equals(fileName)) return true;
        }
        return false;
    }

    private static String[] getAllDirectories() {
        String path = "src/main/resources/DB";
        File file = new File(path);
        return file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
    }


    /*
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
                File folder = new File("your/path");
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("File " + listOfFiles[i].getName());
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
            }

        }

        public static void loadBasicLists(String emailAddress) throws IOException {
            String path = "src/main/resources/DB/" + emailAddress + "/";
            String subPath;
            for (String listName : attributes) {
                subPath = path + listName + ".json";
                if (listName.equals("userBasicData")) {
                    UserBasicData list;
                    list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    getUser(emailAddress).setUserBasicData(list);
                } else if (listName.equals("contacts")) {
                    ArrayList<String> list;
                    list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    getUser(emailAddress).setContacts(list);
                } else {
                    HashMap<UUID, Email> list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    listFactory.setList(listName, emailAddress, list);
                }
            }
        }

        public static void loadSecondaryLists(String emailAddress) throws IOException {
            String listName;
            String path = "src/main/resources/DB/" + emailAddress + "/";
            String subPath;
            for (HashMap.Entry<String, HashMap<UUID, Email>> list : getUser(emailAddress).getOtherFiles().entrySet()) {
                listName = list.getKey();
                subPath = path+listName+".json";
                HashMap<UUID, Email> list2 = mapper.readValue(new File(subPath), new TypeReference<>() {
                });
                listFactory.setList(listName, emailAddress, list2);
            }
        }

     */
    public static void createUserDirectory(String emailAddress) throws Exception {
        String path = "src/main/resources/DB/" + emailAddress;
        HashMap<UUID, Email> emptyMap = new HashMap<>();
        UserBasicData emptyUserBasicData = new UserBasicData();
        ArrayList<String> emptyArray = new ArrayList<>();
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

    public static void createFile(String emailAddress, String fileName) throws Exception {
        String path = "src/main/resources/DB/" + emailAddress + "/" + fileName + ".json";
        HashMap<UUID, Email> map = new HashMap<>();
        getUser(emailAddress).getOtherFiles().put(fileName, map);
        File myObj = new File(path);
        if (!myObj.createNewFile()) throw new Exception();
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
