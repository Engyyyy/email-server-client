package com.example.Backend.Services.FileManipulation;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Email.Email;
import com.example.Backend.Model.Users.User;
import com.example.Backend.Model.Users.UserBasicData;
import com.example.Backend.Model.UsersList;
import com.example.Backend.Services.Factories.ListFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

public class FileManipulation {

    private static final ObjectMapper mapper = new ObjectMapper();
    public static final String[] attributes = new String[]{"allEmails", "contacts", "draft", "receivedEmails", "sentEmails", "trash", "userBasicData", "filterFolders"};
    private static final ListFactory listFactory = new ListFactory();


    public static boolean validateUser(String emailAddress) {
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
                    HashMap<UUID, Contact> contacts = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    getUser(emailAddress).setContacts(contacts);
                } else if (fileName.equals("filterFolders.json")) {
                    ArrayList<String> list = mapper.readValue(new File(subPath), new TypeReference<>() {
                    });
                    getUser(emailAddress).setFilterFolders(list);
                } else {
                    fileName = fileName.replaceAll(".json", "");
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

    private static User getUser(String emailAddress) {
        return UsersList.listOfUsers.get(emailAddress);
    }

    public static boolean checkBasicFiles(String fileName) {
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

    public static void createUserDirectory(String emailAddress) throws Exception {
        String path = "src/main/resources/DB/" + emailAddress;
        HashMap<UUID, Email> emptyMap = new HashMap<>();
        UserBasicData emptyUserBasicData = new UserBasicData();
        Files.createDirectories(Paths.get(path));
        path += "/";
        for (String i : attributes) {
            File myObj = new File(path + i + ".json");
            if (!myObj.createNewFile()) throw new Exception();
            if (i.equals("contacts")) {
                mapper.writeValue(new FileWriter(path + i + ".json"), new HashMap<UUID, Contact>());
            } else if (i.equals("userBasicData")) {
                mapper.writeValue(new FileWriter(path + i + ".json"), emptyUserBasicData);
            } else if (i.equals("filterFolders")) {
                mapper.writeValue(new FileWriter(path + i + ".json"), new ArrayList<String>());
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
        mapper.writeValue(new FileWriter(path), map);
    }

    public static ArrayList<String> getFileNames(String emailAddress) throws Exception {
        System.out.println(emailAddress);
        if(FileManipulation.validateUser(emailAddress)) {
            ArrayList<String> files = new ArrayList<>();
            HashMap<String, HashMap<UUID, Email>> fileLists = getUser(emailAddress).getOtherFiles();
            for (Map.Entry<String, HashMap<UUID, Email>> fileEntry : fileLists.entrySet()) {
                files.add(fileEntry.getKey());
            }
            return files;
        }
        else {
            throw new Exception();
        }
    }

    public static void update(String emailAddress, String listName) throws Exception {
        Object list;
        if (listName.equals("contacts")) {
            list = UsersList.listOfUsers.get(emailAddress).getContacts();
        } else if (listName.equals("filterFolders")) {
            list = UsersList.listOfUsers.get(emailAddress).getFilterFolders();
        } else {
            list = listFactory.getList(listName, emailAddress);
        }
        String path = "src/main/resources/DB/" + emailAddress + "/" + listName + ".json";
        mapper.writeValue(new FileWriter(path), list);
    }


    public static UUID generateId() {
        return UUID.randomUUID();
    }

    public static void deleteFile(String emailAddress, String fileName) throws Exception {
        if (checkBasicFiles(fileName) || !UsersList.listOfUsers.get(emailAddress).getOtherFiles().containsKey(fileName)) {
            throw new Exception();
        } else {
            String path = "src/main/resources/DB/" + emailAddress + "/" + fileName + ".json";
            getUser(emailAddress).getOtherFiles().remove(fileName);
            File file = new File(path);
            if (!file.delete()) throw new Exception();
        }
    }

    public static void renameFile(String emailAddress, String fileName, String newName) throws Exception {
        if (checkBasicFiles(fileName) || checkBasicFiles(newName) || !UsersList.listOfUsers.get(emailAddress).getOtherFiles().containsKey(fileName)) {
            throw new Exception();
        } else {
            String path = "src/main/resources/DB/" + emailAddress + "/" + fileName + ".json";
            String renamedPath = "src/main/resources/DB/" + emailAddress + "/" + newName + ".json";
            getUser(emailAddress).getOtherFiles().put(newName, getUser(emailAddress).getOtherFiles().get(fileName));
            getUser(emailAddress).getOtherFiles().remove(fileName);
            File file = new File(path);
            File rename = new File(renamedPath);
            if (!file.renameTo(rename)) throw new Exception();
        }
    }

    public static void fillFile(UUID[] emails, String emailAddress, String to, String from) throws Exception {
        HashMap<UUID, Email> list = listFactory.getList(from, emailAddress);
        if (UsersList.listOfUsers.get(emailAddress).getOtherFiles().containsKey(to)) {
            for (UUID i : emails) {
                if (list.containsKey(i)) {
                    UsersList.listOfUsers.get(emailAddress).getOtherFiles().get(to).put(i, list.get(i));
                } else throw new Exception();

            }
        } else throw new Exception();
    }

    public static void removeFromFile(UUID[] emails, String emailAddress, String from) throws Exception {
        HashMap<UUID, Email> list = listFactory.getList(from, emailAddress);
        for (UUID i : emails) {
            list.remove(i);
        }
        listFactory.setList(from, emailAddress, list);
    }


}
