package com.euphy.learn.service;

import com.euphy.learn.dao.GenericDao;
import com.euphy.learn.model.RailStation;
import com.euphy.learn.model.RailStationPosition;
import com.euphy.learn.repository.RailStationPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RailStationService {

    private final GenericDao genericDao;
    private final RailStationPositionRepository railStationPositionRepository;

    @Autowired
    public RailStationService(GenericDao genericDao,
                              RailStationPositionRepository railStationPositionRepository) {
        this.genericDao = genericDao;
        this.railStationPositionRepository = railStationPositionRepository;
    }

    public List<RailStation> getAll() {
        return genericDao.findAll(RailStation.class);
    }

    public Map<String, RailStation> mapRailStationsById(List<RailStation> railStations) {
        return railStations.stream()
                .collect(Collectors.toMap(RailStation::getStationId, railStation -> railStation));
    }

    public void saveAll(List<RailStationPosition> railStationPositions) {
        railStationPositionRepository.saveAll(railStationPositions);
    }

    public void deleteAll() {
        genericDao.deleteAll(RailStationPosition.class);
        genericDao.deleteAll(RailStation.class);
    }
}
