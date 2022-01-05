package com.improving.energyhub.bean;

public class Update {
    private float ambientTemp;

    private String mode;

    private float heatTemp;

    private boolean schedule;

    public float getAmbientTemp() {
        return ambientTemp;
    }

    public void setAmbientTemp(float ambientTemp) {
        this.ambientTemp = ambientTemp;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public float getHeatTemp() {
        return heatTemp;
    }

    public void setHeatTemp(float heatTemp) {
        this.heatTemp = heatTemp;
    }

    public boolean isSchedule() {
        return schedule;
    }

    public void setSchedule(boolean schedule) {
        this.schedule = schedule;
    }
}
