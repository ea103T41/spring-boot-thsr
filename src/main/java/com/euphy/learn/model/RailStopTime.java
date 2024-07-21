package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "rail_stop_time")
public class RailStopTime implements Serializable {

    private Integer id;
    private Integer stopSequence;
    private String stationId;
    private String arrivalTime;
    private String departureTime;
    private RailGeneralTimetable generalTimetable;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public Integer getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(Integer stopSequence) {
        this.stopSequence = stopSequence;
    }

    @Column(name = "station_id")
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Column
    @Temporal(TemporalType.TIME)
    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Column
    @Temporal(TemporalType.TIME)
    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "general_timetable_id")
    public RailGeneralTimetable getGeneralTimetable() {
        return generalTimetable;
    }

    public void setGeneralTimetable(RailGeneralTimetable generalTimetable) {
        this.generalTimetable = generalTimetable;
    }
}
