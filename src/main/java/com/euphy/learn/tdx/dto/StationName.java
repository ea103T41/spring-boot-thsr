package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationName {
    private String zh_tw;
    private String en;

    public String getZh_tw() {
        return zh_tw;
    }

    @JsonProperty("Zh_tw")
    public void setZh_tw(String zh_tw) {
        this.zh_tw = zh_tw;
    }

    public String getEn() {
        return en;
    }

    @JsonProperty("En")
    public void setEn(String en) {
        this.en = en;
    }
}
