package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * 座標
 */
@Entity
@Table(name = "rail_station_position")
public class RailStationPosition implements Serializable {

    private Integer id;
    private Double positionLat;
    private Double positionLon;
    private String geoHash;
    private RailStation railStation;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public Double getPositionLat() {
        return positionLat;
    }

    public void setPositionLat(Double positionLat) {
        this.positionLat = positionLat;
    }

    @Column
    public Double getPositionLon() {
        return positionLon;
    }

    public void setPositionLon(Double positionLon) {
        this.positionLon = positionLon;
    }

    @Column
    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
//    @JoinColumn(name = "rail_station_id")
    public RailStation getRailStation() {
        return railStation;
    }

    public void setRailStation(RailStation railStation) {
        this.railStation = railStation;
    }
}
