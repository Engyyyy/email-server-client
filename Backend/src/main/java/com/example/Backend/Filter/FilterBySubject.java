package com.example.Backend.Filter;

import com.example.Backend.Model.Email;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
@Component
public class FilterBySubject implements FilterI {

    @Override
    public ArrayList<Email> meetCriteria(HashMap<UUID, Email> list, String subject) {
        ArrayList<Email> filteredEmails = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : list.entrySet()) {
            if (emailEntry.getValue().getHeader().getSubject().equals(subject)) {
                filteredEmails.add(emailEntry.getValue());
            }
        }
        return filteredEmails;
    }
}
