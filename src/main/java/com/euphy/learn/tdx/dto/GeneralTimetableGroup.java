package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralTimetableGroup extends Version {

    private String effectiveDate;
    private String expiringDate;
    private GeneralTimetable generalTimetable;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    @JsonProperty("EffectiveDate")
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpiringDate() {
        return expiringDate;
    }

    @JsonProperty("ExpiringDate")
    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }

    public GeneralTimetable getGeneralTimetable() {
        return generalTimetable;
    }

    @JsonProperty("GeneralTimetable")
    public void setGeneralTimetable(GeneralTimetable generalTimetable) {
        this.generalTimetable = generalTimetable;
    }
}
