package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "rail_general_train_info")
public class RailGeneralTrainInfo implements Serializable {

    private Integer id;
    private String trainNo;
    private Integer direction;
    private String startingStationId;
    private String endingStationId;
    private String note;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    @Column
    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    @Column(name = "starting_station_id")
    public String getStartingStationId() {
        return startingStationId;
    }

    public void setStartingStationId(String startingStationId) {
        this.startingStationId = startingStationId;
    }

    @Column(name = "ending_station_id")
    public String getEndingStationId() {
        return endingStationId;
    }

    public void setEndingStationId(String endingStationId) {
        this.endingStationId = endingStationId;
    }

    @Column
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
