package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "rail_station")
public class RailStation implements Serializable {
    private Integer id;
    private String stationId;
    private String stationCode;
    private String stationName;
    private String stationAddress;
//    private RailStationPosition railStationPosition;
    private String locationCityCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "station_id", unique = true, nullable = false)
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Column
    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    @Column
    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

//    @OneToOne(
//            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
//            mappedBy = "railStation", orphanRemoval = true)
//            /* mappedBy will cause N+1 query even using lazy loading */
//    public RailStationPosition getRailStationPosition() {
//        return railStationPosition;
//    }
//
//    public void setRailStationPosition(RailStationPosition railStationPosition) {
//        this.railStationPosition = railStationPosition;
//    }

    @Column
    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    @Column
    public String getLocationCityCode() {
        return locationCityCode;
    }

    public void setLocationCityCode(String locationCityCode) {
        this.locationCityCode = locationCityCode;
    }

}
