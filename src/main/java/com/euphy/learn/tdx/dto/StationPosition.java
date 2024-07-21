package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StationPosition {

    private Double positionLat;
    private Double positionLon;
    private String geoHash;

    public Double getPositionLat() {
        return positionLat;
    }

    @JsonProperty("PositionLat")
    public void setPositionLat(String positionLat) {
        this.positionLat = Double.parseDouble(positionLat);
    }

    public Double getPositionLon() {
        return positionLon;
    }

    @JsonProperty("PositionLon")
    public void setPositionLon(Double positionLon) {
        this.positionLon = positionLon;
    }

    public String getGeoHash() {
        return geoHash;
    }

    @JsonProperty("GeoHash")
    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }
}
