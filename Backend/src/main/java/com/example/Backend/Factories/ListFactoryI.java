package com.example.Backend.Factories;

import com.example.Backend.Model.Email;

import java.util.HashMap;
import java.util.UUID;

public interface ListFactoryI {
    public void setList(String listName, String emailAdress, HashMap<UUID, Email> list) throws Exception;

    public HashMap<UUID, Email> getList(String listName, String emailAdress) throws Exception;
}
