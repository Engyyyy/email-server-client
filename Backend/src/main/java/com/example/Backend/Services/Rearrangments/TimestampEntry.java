package com.example.Backend.Services.Rearrangments;

import com.example.Backend.Model.Email.Email;

public class TimestampEntry implements Comparable<TimestampEntry>  {
    private long key;
    private Email value;

    public TimestampEntry(long key, Email value) {
        this.key = key;
        this.value = value;
    }

    public long getKey() {
        return key;
    }

    public Email getValue() {
        return value;
    }

    @Override
    public int compareTo(TimestampEntry other) {
        return Long.compare(other.getKey(), this.getKey());
    }


}