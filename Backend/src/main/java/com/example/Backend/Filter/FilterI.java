package com.example.Backend.Filter;

import com.example.Backend.Model.Email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public interface FilterI {
    public ArrayList<Email> meetCriteria(HashMap<UUID, Email> list, String criteria);
}
