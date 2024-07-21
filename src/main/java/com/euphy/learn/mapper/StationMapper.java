package com.euphy.learn.mapper;

import com.euphy.learn.tdx.dto.Station;
import com.euphy.learn.tdx.dto.StationPosition;
import com.euphy.learn.model.RailStation;
import com.euphy.learn.model.RailStationPosition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StationMapper {

    public RailStationPosition toRailStationPosition(Station station) {
        RailStation rs = new RailStation();

        rs.setStationId(station.getStationId());
        rs.setStationCode(station.getStationCode());
        rs.setStationName(station.getStationName().getZh_tw());
        rs.setStationAddress(station.getStationAddress());
        rs.setLocationCityCode(station.getLocationCityCode());

        StationPosition sp = station.getStationPosition();
        RailStationPosition rsp = new RailStationPosition();
        rsp.setPositionLat(sp.getPositionLat());
        rsp.setPositionLon(sp.getPositionLon());
        rsp.setGeoHash(sp.getGeoHash());

        rsp.setRailStation(rs);
        return rsp;
    }

    public Map<String, RailStation> mapRailStationsById(List<RailStation> railStations) {
        return railStations.stream()
                .collect(Collectors.toMap(RailStation::getStationId, railStation -> railStation));
    }
}
