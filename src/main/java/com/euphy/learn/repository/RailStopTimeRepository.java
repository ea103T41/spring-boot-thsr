package com.euphy.learn.repository;

import com.euphy.learn.model.RailStopTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RailStopTimeRepository extends JpaRepository<RailStopTime, Integer> {

    List<RailStopTime> findByGeneralTimetableId(Integer gtId);

    List<RailStopTime> findByGeneralTimetableIdInAndStationId(List<Integer> gtIds, String stationId);
}
