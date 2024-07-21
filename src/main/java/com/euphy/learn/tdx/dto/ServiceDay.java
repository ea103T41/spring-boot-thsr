package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceDay {

    private Integer monday;
    private Integer tuesday;
    private Integer wednesday;
    private Integer thursday;
    private Integer friday;
    private Integer saturday;
    private Integer sunday;

    public Integer getMonday() {
        return monday;
    }

    @JsonProperty("Monday")
    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    public Integer getTuesday() {
        return tuesday;
    }

    @JsonProperty("Tuesday")
    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    public Integer getWednesday() {
        return wednesday;
    }

    @JsonProperty("Wednesday")
    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    public Integer getThursday() {
        return thursday;
    }

    @JsonProperty("Thursday")
    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    public Integer getFriday() {
        return friday;
    }

    @JsonProperty("Friday")
    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    public Integer getSaturday() {
        return saturday;
    }

    @JsonProperty("Saturday")
    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    public Integer getSunday() {
        return sunday;
    }

    @JsonProperty("Sunday")
    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

}
