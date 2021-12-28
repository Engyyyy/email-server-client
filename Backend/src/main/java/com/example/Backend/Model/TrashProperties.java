package com.example.Backend.Model;

import java.sql.Timestamp;

public class TrashProperties {
    private  String initialList;
    private long timestamp;

    TrashProperties(){}
    public TrashProperties(String initialList) {
        this.initialList = initialList;
        this.timestamp = System.currentTimeMillis();
    }

    public String getInitialList() {
        return initialList;
    }

    public void setInitialList(String initialList) {
        this.initialList = initialList;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
