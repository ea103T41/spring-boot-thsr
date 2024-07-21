package com.euphy.learn.repository;

import com.euphy.learn.model.RailStationPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailStationPositionRepository extends JpaRepository<RailStationPosition, Integer> {

}
