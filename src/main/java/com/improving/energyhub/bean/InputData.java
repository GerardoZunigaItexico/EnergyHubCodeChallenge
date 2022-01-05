package com.improving.energyhub.bean;

import java.io.File;
import java.util.Date;

public class InputData {

    private String eventName;

    private File jsonFile;

    private Date dateFilter;

    public InputData(String fetchEvent, File file, Date date) {
        this.eventName = fetchEvent;
        this.jsonFile = file;
        this.dateFilter = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public File getJsonFile() {
        return jsonFile;
    }

    public void setJsonFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public Date getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(Date dateFilter) {
        this.dateFilter = dateFilter;
    }
}
