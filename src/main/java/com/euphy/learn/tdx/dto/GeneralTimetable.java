package com.euphy.learn.tdx.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeneralTimetable {
    private GeneralTrainInfo generalTrainInfo;
    private List<StopTime> stopTimes;
    private ServiceDay serviceDay;
    private String srcUpdateTime;

    public GeneralTrainInfo getGeneralTrainInfo() {
        return generalTrainInfo;
    }

    @JsonProperty("GeneralTrainInfo")
    public void setGeneralTrainInfo(GeneralTrainInfo generalTrainInfo) {
        this.generalTrainInfo = generalTrainInfo;
    }

    public List<StopTime> getStopTimes() {
        return stopTimes;
    }

    @JsonProperty("StopTimes")
    public void setStopTimes(List<StopTime> stopTimes) {
        this.stopTimes = stopTimes;
    }

    public ServiceDay getServiceDay() {
        return serviceDay;
    }

    @JsonProperty("ServiceDay")
    public void setServiceDay(ServiceDay serviceDay) {
        this.serviceDay = serviceDay;
    }

    public String getSrcUpdateTime() {
        return srcUpdateTime;
    }

    @JsonProperty("SrcUpdateTime")
    public void setSrcUpdateTime(String srcUpdateTime) {
        this.srcUpdateTime = srcUpdateTime;
    }
}
