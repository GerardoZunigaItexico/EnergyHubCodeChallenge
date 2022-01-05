package com.improving.energyhub.bean;

import java.time.LocalDate;
import java.util.Date;

public class Event {

    private Date updateTime;

    private Update update;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
