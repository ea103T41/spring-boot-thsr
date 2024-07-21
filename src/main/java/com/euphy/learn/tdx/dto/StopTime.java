package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StopTime {

    private Integer stopSequence;
    private String stationId;
    private StationName stationName;
    private String arrivalTime;
    private String departureTime;

    public Integer getStopSequence() {
        return stopSequence;
    }

    @JsonProperty("StopSequence")
    public void setStopSequence(Integer stopSequence) {
        this.stopSequence = stopSequence;
    }

    public String getStationId() {
        return stationId;
    }

    @JsonProperty("StationID")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public StationName getStationName() {
        return stationName;
    }

    @JsonProperty("StationName")
    public void setStationName(StationName stationName) {
        this.stationName = stationName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    @JsonProperty("ArrivalTime")
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    @JsonProperty("DepartureTime")
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
}
