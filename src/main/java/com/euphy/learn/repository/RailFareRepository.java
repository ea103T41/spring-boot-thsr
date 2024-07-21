package com.euphy.learn.repository;

import com.euphy.learn.model.RailFare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RailFareRepository extends JpaRepository<RailFare, Integer> {

    Optional<RailFare> findByOriginStationIdAndDestinationStationId(String fromStationID, String toStationID);
}
