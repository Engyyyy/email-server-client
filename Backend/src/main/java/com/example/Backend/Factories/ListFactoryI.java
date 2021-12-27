package com.example.Backend.Factories;

import com.example.Backend.Model.Email;

import java.util.HashMap;
import java.util.UUID;

public interface ListFactoryI {
    public void setList(String listName, String emailAdress, HashMap<UUID, Email> list);

    public HashMap<UUID, Email> getList(String listName, String emailAdress);
}
