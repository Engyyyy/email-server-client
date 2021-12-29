package com.example.Backend.Services.Rearrangments;

import com.example.Backend.Model.Email.Email;

public class StringEmailEntry implements Comparable<StringEmailEntry> {
    private String key;
    private Email value;

    public StringEmailEntry(String key, Email value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Email getValue() {
        return value;
    }

    @Override
    public int compareTo(StringEmailEntry other) {
        return this.getKey().compareTo(other.getKey());
    }

}
