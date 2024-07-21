package com.euphy.learn.mapper;

import com.euphy.learn.dto.StopTimeDetail;
import com.euphy.learn.dto.StopTimeInfo;
import com.euphy.learn.model.RailStation;
import com.euphy.learn.model.RailStopTime;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StopTimeMapper {

    public StopTimeInfo toStopTimeInfo(RailStopTime railStopTime) {
        StopTimeInfo stopTimeInfo = new StopTimeInfo();
        stopTimeInfo.setGtId(railStopTime.getGeneralTimetable().getId());
        stopTimeInfo.setDepartureTime(railStopTime.getDepartureTime());
        stopTimeInfo.setTrainNo(railStopTime.getGeneralTimetable().getGeneralTrainInfo().getTrainNo());

        return stopTimeInfo;
    }

    public StopTimeDetail toStopTimeDetail(RailStopTime railStopTime, Map<String, RailStation> railStations) {
        return new StopTimeDetail(
                railStopTime.getGeneralTimetable().getId(),
                railStopTime.getDepartureTime(),
                railStopTime.getArrivalTime(),
                railStations.get(railStopTime.getStationId()).getStationName()
        );
    }
}
