package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Station extends Version {

    private String stationUid;
    private String stationId;
    private String stationCode;
    private StationName stationName;
    private String stationAddress;
    private String stationPhone;
    private String operatorId;
    private StationPosition stationPosition;
    private String locationCity;
    private String locationCityCode;
    private String locationTown;
    private String locationTownCode;

    public String getStationUid() {
        return stationUid;
    }

    @JsonProperty("StationUID")
    public void setStationUid(String stationUid) {
        this.stationUid = stationUid;
    }

    public String getStationId() {
        return stationId;
    }

    @JsonProperty("StationID")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    @JsonProperty("StationCode")
    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public StationName getStationName() {
        return stationName;
    }

    @JsonProperty("StationName")
    public void setStationName(StationName stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    @JsonProperty("StationAddress")
    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getStationPhone() {
        return stationPhone;
    }

    @JsonProperty("StationPhone")
    public void setStationPhone(String stationPhone) {
        this.stationPhone = stationPhone;
    }

    public String getOperatorId() {
        return operatorId;
    }

    @JsonProperty("OperatorID")
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public StationPosition getStationPosition() {
        return stationPosition;
    }

    @JsonProperty("StationPosition")
    public void setStationPosition(StationPosition stationPosition) {
        this.stationPosition = stationPosition;
    }

    public String getLocationCity() {
        return locationCity;
    }

    @JsonProperty("LocationCity")
    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationCityCode() {
        return locationCityCode;
    }

    @JsonProperty("LocationCityCode")
    public void setLocationCityCode(String locationCityCode) {
        this.locationCityCode = locationCityCode;
    }

    public String getLocationTown() {
        return locationTown;
    }

    @JsonProperty("LocationTown")
    public void setLocationTown(String locationTown) {
        this.locationTown = locationTown;
    }

    public String getLocationTownCode() {
        return locationTownCode;
    }

    @JsonProperty("LocationTownCode")
    public void setLocationTownCode(String locationTownCode) {
        this.locationTownCode = locationTownCode;
    }
}
