package com.example.Backend.Rearrangments;

import com.example.Backend.Model.Email;

public class PriorityEntry implements Comparable<PriorityEntry>  {
    private int key;
    private Email value;

    public PriorityEntry(int key, Email value) {
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
    public int compareTo(PriorityEntry other) {
        return Integer.compare(other.getKey(), this.getKey());
    }


}