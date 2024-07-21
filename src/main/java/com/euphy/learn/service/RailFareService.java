package com.euphy.learn.service;

import com.euphy.learn.dao.GenericDao;
import com.euphy.learn.dto.*;
import com.euphy.learn.model.Fare;
import com.euphy.learn.model.RailFare;
import com.euphy.learn.repository.RailFareRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class RailFareService {

    private final GenericDao genericDao;
    private final RailFareRepository railFareRepository;

    @Autowired
    public RailFareService(GenericDao genericDao, RailFareRepository railFareRepository) {
        this.genericDao = genericDao;
        this.railFareRepository = railFareRepository;
    }

    public List<FareInfo> searchFare(String fromStationID, String toStationID) {
        Optional<RailFare> railFareOptional = railFareRepository.findByOriginStationIdAndDestinationStationId(fromStationID, toStationID);
        RailFare railFare = railFareOptional.orElseThrow(() -> new RuntimeException("No rail fare found"));
        return toFareInfo(railFare);
    }

    public void saveAll(List<RailFare> railFares) {
        railFareRepository.saveAll(railFares);
    }

    public void deleteAll() {
        genericDao.deleteAll(Fare.class);
        genericDao.deleteAll(RailFare.class);
    }

    private List<FareInfo> toFareInfo(RailFare railFare) {
        Map<FareType, FareInfo> fareInfoMap = new EnumMap<>(FareType.class);

        List<Fare> fares = railFare.getFares();
        for (Fare fare : fares) {
            FareType fareType = mapCabinClassToFareType(fare.getCabinClass());
            FareInfo fareInfo = fareInfoMap.computeIfAbsent(fareType, k -> new FareInfo(fareType));
            setPrice(fareInfo, fare);
        }
        return fareInfoMap.values().stream().toList();
    }

    private FareType mapCabinClassToFareType(int cabinClass) {
        return switch (cabinClass) {
            case 1 -> FareType.STANDARD;
            case 2 -> FareType.BUSINESS;
            case 3 -> FareType.NON_RESERVED;
            default -> throw new IllegalStateException("Unexpected value: " + cabinClass);
        };
    }

    private void setPrice(FareInfo fareBase, Fare fare) {
        if (fare.getTicketType() == 1) {
            if (fare.getFareClass() == 1) {
                fareBase.setAdultPrice(fare.getPrice());
            }
            else if (fare.getFareClass() == 9) {
                fareBase.setConcessionPrice(fare.getPrice());
            }
        }
        else if (fare.getTicketType() == 8) {
            fareBase.setGroupPrice(fare.getPrice());
        }
    }

}
