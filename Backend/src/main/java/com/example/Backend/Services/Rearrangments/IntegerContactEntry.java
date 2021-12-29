package com.example.Backend.Services.Rearrangments;

import com.example.Backend.Model.Contacts.Contact;
import com.example.Backend.Model.Email.Email;

public class IntegerContactEntry implements Comparable<IntegerContactEntry>{
    private int key;
    private Contact value;

    public IntegerContactEntry(int key, Contact value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public Contact getValue() {
        return value;
    }

    @Override
    public int compareTo(IntegerContactEntry other) {
        return Integer.compare(this.getKey(), other.getKey());
    }
}
