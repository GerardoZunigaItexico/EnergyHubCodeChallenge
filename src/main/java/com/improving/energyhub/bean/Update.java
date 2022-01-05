package com.improving.energyhub.bean;

import java.util.Date;

public class Update {
    private Float ambientTemp;

    private String mode;

    private Float heatTemp;

    private Boolean schedule;

    private Float coolTemp;

    private Date lastAlertTs;

    public Float getAmbientTemp() {
        return ambientTemp;
    }

    public void setAmbientTemp(Float ambientTemp) {
        this.ambientTemp = ambientTemp;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Float getHeatTemp() {
        return heatTemp;
    }

    public void setHeatTemp(Float heatTemp) {
        this.heatTemp = heatTemp;
    }

    public Boolean getSchedule() {
        return schedule;
    }

    public void setSchedule(Boolean schedule) {
        this.schedule = schedule;
    }

    public Float getCoolTemp() {
        return coolTemp;
    }

    public void setCoolTemp(Float coolTemp) {
        this.coolTemp = coolTemp;
    }

    public Date getLastAlertTs() {
        return lastAlertTs;
    }

    public void setLastAlertTs(Date lastAlertTs) {
        this.lastAlertTs = lastAlertTs;
    }
}
