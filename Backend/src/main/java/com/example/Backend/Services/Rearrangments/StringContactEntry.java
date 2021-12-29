package com.example.Backend.Services.Rearrangments;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Email.Email;

public class StringContactEntry implements Comparable<StringContactEntry>{
    private String key;
    private Contact value;

    public StringContactEntry(String key, Contact value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public  Contact getValue() {
        return value;
    }

    @Override
    public int compareTo(StringContactEntry other) {
        return this.getKey().compareTo(other.getKey());
    }


}
