package com.euphy.learn.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rail_general_timetable")
public class RailGeneralTimetable implements Serializable {

    private Integer id;
    private RailGeneralTrainInfo generalTrainInfo; // 定期車次資料
    private List<RailStopTime> stopTimes; // 停靠時間資料
    private RailServiceDay serviceDay; // 營運日型態

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "general_train_info_id")
    public RailGeneralTrainInfo getGeneralTrainInfo() {
        return generalTrainInfo;
    }

    public void setGeneralTrainInfo(RailGeneralTrainInfo generalTrainInfo) {
        this.generalTrainInfo = generalTrainInfo;
    }

    @OneToMany(
            cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "generalTimetable")
    public List<RailStopTime> getStopTimes() {
        return stopTimes;
    }

    public void setStopTimes(List<RailStopTime> stopTimes) {
        this.stopTimes = stopTimes;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "service_day_id")
    public RailServiceDay getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(RailServiceDay serviceDay) {
        this.serviceDay = serviceDay;
    }

}
