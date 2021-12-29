package com.example.Backend.Services.Filter;

import com.example.Backend.Model.Email.Email;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Filter {
    @Autowired
    FilterBySender filterBySender;
    @Autowired
    FilterBySubject filterBySubject;

    public ArrayList<Email> bySender(HashMap<UUID, Email> list, String criteria) {
        return filterBySender.meetCriteria(list, criteria);
    }

    public ArrayList<Email> bySubject(HashMap<UUID, Email> list, String criteria) {
        return filterBySubject.meetCriteria(list, criteria);
    }
}
