package com.example.Backend.Model;

import java.sql.Timestamp;
import java.util.Date;

public class TrashProperties {
    private  String initialList;
    private Date startDate;

    TrashProperties(){}
    public TrashProperties(String initialList) {
        this.initialList = initialList;
        this.startDate = new Date();
    }

    public String getInitialList() {
        return initialList;
    }

    public void setInitialList(String initialList) {
        this.initialList = initialList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
