package com.example.Backend.Filter;

import com.example.Backend.Model.Email;
import com.example.Backend.Model.User;
import com.example.Backend.Model.UsersList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
@Component
public class FilterBySender implements FilterI {

    @Override
    public ArrayList<Email> meetCriteria(HashMap<UUID, Email> list, String senderEmailAddress) {
        ArrayList<Email> filteredEmails = new ArrayList<>();
        for (HashMap.Entry<UUID, Email> emailEntry : list.entrySet()) {
            if (emailEntry.getValue().getHeader().getSenderEmailAddress().equals(senderEmailAddress)) {
                filteredEmails.add(emailEntry.getValue());
            }
        }
        return filteredEmails;
    }

}
