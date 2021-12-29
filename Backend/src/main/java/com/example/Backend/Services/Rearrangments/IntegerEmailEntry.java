package com.example.Backend.Services.Rearrangments;

import com.example.Backend.Model.Email.Email;

public class IntegerEmailEntry implements Comparable<IntegerEmailEntry> {
    private int key;
    private Email value;

    public IntegerEmailEntry(int key, Email value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public Email getValue() {
        return value;
    }

    @Override
    public int compareTo(IntegerEmailEntry other) {
        return Integer.compare(this.getKey(), other.getKey());
    }


}