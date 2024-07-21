package com.euphy.learn.mapper;

import com.euphy.learn.tdx.dto.GeneralTimetable;
import com.euphy.learn.tdx.dto.GeneralTrainInfo;
import com.euphy.learn.tdx.dto.ServiceDay;
import com.euphy.learn.tdx.dto.StopTime;
import com.euphy.learn.model.RailGeneralTimetable;
import com.euphy.learn.model.RailGeneralTrainInfo;
import com.euphy.learn.model.RailServiceDay;
import com.euphy.learn.model.RailStopTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneralTimetableMapper {

    public RailGeneralTimetable toEntity(GeneralTimetable gt) {
        RailGeneralTimetable rgt = new RailGeneralTimetable();

        RailGeneralTrainInfo rgtInfo = toEntity(gt.getGeneralTrainInfo());
        rgt.setGeneralTrainInfo(rgtInfo);

        List<RailStopTime> stopTimes = toEntity(rgt, gt.getStopTimes());
        rgt.setStopTimes(stopTimes);

        RailServiceDay serviceDay = toEntity(gt.getServiceDay());
        rgt.setServiceDay(serviceDay);

        return rgt;
    }

    private RailGeneralTrainInfo toEntity(GeneralTrainInfo gtInfo) {
        RailGeneralTrainInfo rgtInfo = new RailGeneralTrainInfo();
        rgtInfo.setTrainNo(gtInfo.getTrainNo());
        rgtInfo.setDirection(gtInfo.getDirection());
        rgtInfo.setStartingStationId(gtInfo.getStartingStationId());
        rgtInfo.setEndingStationId(gtInfo.getEndingStationId());
        rgtInfo.setNote(gtInfo.getNote().toString());
        return rgtInfo;
    }

    private List<RailStopTime> toEntity(RailGeneralTimetable rgt, List<StopTime> stopTimes) {
        List<RailStopTime> rgtStopTimes = new ArrayList<>();

        for (StopTime stopTime : stopTimes) {
            RailStopTime rgtStopTime = new RailStopTime();
            rgtStopTime.setStopSequence(stopTime.getStopSequence());
            rgtStopTime.setStationId(stopTime.getStationId());
            rgtStopTime.setArrivalTime(stopTime.getArrivalTime());
            rgtStopTime.setDepartureTime(stopTime.getDepartureTime());
            rgtStopTime.setGeneralTimetable(rgt);
            rgtStopTimes.add(rgtStopTime);
        }
        return rgtStopTimes;
    }

    private RailServiceDay toEntity(ServiceDay serviceDay) {
        RailServiceDay rgtServiceDay = new RailServiceDay();
        rgtServiceDay.setMonday(serviceDay.getMonday());
        rgtServiceDay.setTuesday(serviceDay.getTuesday());
        rgtServiceDay.setWednesday(serviceDay.getWednesday());
        rgtServiceDay.setThursday(serviceDay.getThursday());
        rgtServiceDay.setFriday(serviceDay.getFriday());
        rgtServiceDay.setSaturday(serviceDay.getSaturday());
        rgtServiceDay.setSunday(serviceDay.getSunday());

        return rgtServiceDay;
    }

}
