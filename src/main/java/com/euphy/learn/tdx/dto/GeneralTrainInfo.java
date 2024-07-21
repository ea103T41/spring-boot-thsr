package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralTrainInfo {

    private String trainNo;
    private Integer direction;
    private String startingStationId;
    private StationName startingStationName;
    private String endingStationId;
    private StationName endingStationName;
    private Object note;

    public String getTrainNo() {
        return trainNo;
    }

    @JsonProperty("TrainNo")
    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public Integer getDirection() {
        return direction;
    }

    @JsonProperty("Direction")
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getStartingStationId() {
        return startingStationId;
    }

    @JsonProperty("StartingStationID")
    public void setStartingStationId(String startingStationId) {
        this.startingStationId = startingStationId;
    }

    public StationName getStartingStationName() {
        return startingStationName;
    }

    @JsonProperty("StartingStationName")
    public void setStartingStationName(StationName startingStationName) {
        this.startingStationName = startingStationName;
    }

    public String getEndingStationId() {
        return endingStationId;
    }

    @JsonProperty("EndingStationID")
    public void setEndingStationId(String endingStationId) {
        this.endingStationId = endingStationId;
    }

    public StationName getEndingStationName() {
        return endingStationName;
    }

    @JsonProperty("EndingStationName")
    public void setEndingStationName(StationName endingStationName) {
        this.endingStationName = endingStationName;
    }

    public Object getNote() {
        return note;
    }

    @JsonProperty("Note")
    public void setNote(Object note) {
        this.note = note;
    }
}
