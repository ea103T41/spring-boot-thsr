package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

/**
 * 臺鐵起迄站間票價資料
 */
@Entity
@Table(name = "rail_fare")
public class RailFare implements Serializable {
    private Integer id;
    private String originStationId;//起點車站代碼
    private String destinationStationId; // 迄點車站代碼
    private Integer direction; // 行駛方向 : [0:'南下',1:'北上']
    private List<Fare> fares = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "origin_station_id")
    public String getOriginStationId() {
        return originStationId;
    }

    public void setOriginStationId(String originStationId) {
        this.originStationId = originStationId;
    }

    @Column(name = "destination_station_id")
    public String getDestinationStationId() {
        return destinationStationId;
    }

    public void setDestinationStationId(String destinationStationId) {
        this.destinationStationId = destinationStationId;
    }

    @Column
    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    @OneToMany(
            cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "railFare")
    public List<Fare> getFares() {
        return fares;
    }

    public void setFares(List<Fare> fares) {
        this.fares = fares;
    }

}
