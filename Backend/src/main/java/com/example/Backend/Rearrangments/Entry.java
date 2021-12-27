package com.example.Backend.Rearrangments;

import com.example.Backend.Model.Email;

public class Entry  {
    private String key;
    private Email value;

    public Entry(String key, Email value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Email getValue() {
        return value;
    }

    public void setValue(Email value) {
        this.value = value;
    }


}