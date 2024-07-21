package com.euphy.learn.service;

import com.euphy.learn.dao.RailStopTimeDao;
import com.euphy.learn.dto.StopTimeDetail;
import com.euphy.learn.dto.StopTimeInfo;
import com.euphy.learn.mapper.StationMapper;
import com.euphy.learn.mapper.StopTimeMapper;
import com.euphy.learn.model.RailStation;
import com.euphy.learn.model.RailStopTime;
import com.euphy.learn.repository.RailStopTimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class RailStopTimeService {

    private final RailStopTimeDao railStopTimeDao;
    private final RailStopTimeRepository railStopTimeRepository;
    private final StopTimeMapper stopTimeMapper;
    private final StationMapper stationMapper;

    @Autowired
    public RailStopTimeService(RailStopTimeDao railStopTimeDao,
                               RailStopTimeRepository railStopTimeRepository,
                               StopTimeMapper stopTimeMapper,
                               StationMapper stationMapper) {
        this.railStopTimeDao = railStopTimeDao;
        this.railStopTimeRepository = railStopTimeRepository;
        this.stopTimeMapper = stopTimeMapper;
        this.stationMapper = stationMapper;
    }

    public List<StopTimeInfo> searchStopTime(String fromStationID,
                                             String toStationID,
                                             String trainDate,
                                             String startTime) {

        LocalDate searchDate = LocalDate.parse(trainDate);
        String serviceDay = searchDate.getDayOfWeek().name().toLowerCase();

        List<StopTimeInfo> stopTimeInfoList = new ArrayList<>();
        List<RailStopTime> resultList = railStopTimeDao.getStopTimes(fromStationID, toStationID, serviceDay, startTime);

        Map<Integer, RailStopTime> stopTimeMap = railStopTimeRepository.findByGeneralTimetableIdInAndStationId(
                resultList.stream()
                        .map(rst -> rst.getGeneralTimetable().getId())
                        .toList(), toStationID)
                .stream()
                .collect(Collectors.toMap(
                        stop -> stop.getGeneralTimetable().getId(), stop -> stop));

        int count = 5;
        for (RailStopTime railStopTime : resultList) {
            if (count <= 0) {
                break;
            }
            StopTimeInfo stopTimeInfo = stopTimeMapper.toStopTimeInfo(railStopTime);
            RailStopTime destinationStop = stopTimeMap.get(railStopTime.getGeneralTimetable().getId());
            // 未停訖站
            if (destinationStop == null) {
                continue;
            }
            stopTimeInfo.setArrivalTime(destinationStop.getArrivalTime());
            stopTimeInfo.setTripTime(this.calculateTripTime(stopTimeInfo.getDepartureTime(), stopTimeInfo.getArrivalTime()));
            stopTimeInfoList.add(stopTimeInfo);
            count--;
        }
        return stopTimeInfoList;
    }

    public List<StopTimeDetail> searchStopTime(List<RailStation> railStations, Integer gtId) {
        Map<String, RailStation> stationMap = stationMapper.mapRailStationsById(railStations);
        List<RailStopTime> resultList = railStopTimeRepository.findByGeneralTimetableId(gtId);

        return resultList.stream()
                .map(railStopTime -> stopTimeMapper.toStopTimeDetail(railStopTime, stationMap))
                .toList();
    }

    private String calculateTripTime(String departureTime, String arrivalTime) {
        long duration = Duration.between(LocalTime.parse(departureTime), LocalTime.parse(arrivalTime))
                .getSeconds();
        return LocalTime.ofSecondOfDay(duration).toString().substring(0, 5);
    }
}
