package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ODFare extends Version {
    private String originStationId;
    private StationName originStationName;
    private String destinationStationId;
    private StationName destinationStationName;
    private Integer direction;
    private List<FareDto> fares;
    private String srcUpdateTime;

    public String getOriginStationId() {
        return originStationId;
    }

    @JsonProperty("OriginStationID")
    public void setOriginStationId(String originStationId) {
        this.originStationId = originStationId;
    }

    public StationName getOriginStationName() {
        return originStationName;
    }

    @JsonProperty("OriginStationName")
    public void setOriginStationName(StationName originStationName) {
        this.originStationName = originStationName;
    }

    public String getDestinationStationId() {
        return destinationStationId;
    }

    @JsonProperty("DestinationStationID")
    public void setDestinationStationId(String destinationStationId) {
        this.destinationStationId = destinationStationId;
    }

    public StationName getDestinationStationName() {
        return destinationStationName;
    }

    @JsonProperty("DestinationStationName")
    public void setDestinationStationName(StationName destinationStationName) {
        this.destinationStationName = destinationStationName;
    }

    public Integer getDirection() {
        return direction;
    }

    @JsonProperty("Direction")
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public List<FareDto> getFares() {
        return fares;
    }

    @JsonProperty("Fares")
    public void setFares(List<FareDto> fares) {
        this.fares = fares;
    }

    public String getSrcUpdateTime() {
        return srcUpdateTime;
    }

    @JsonProperty("SrcUpdateTime")
    public void setSrcUpdateTime(String srcUpdateTime) {
        this.srcUpdateTime = srcUpdateTime;
    }

}
