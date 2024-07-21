package com.euphy.learn.mapper;

import com.euphy.learn.tdx.dto.ODFare;
import com.euphy.learn.model.Fare;
import com.euphy.learn.model.RailFare;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OdFareMapper {

    public RailFare toEntity(ODFare odFare) {

        RailFare railFare = new RailFare();
        railFare.setOriginStationId(odFare.getOriginStationId());
        railFare.setDestinationStationId(odFare.getDestinationStationId());
        railFare.setDirection(odFare.getDirection());

        List<Fare> fares = new ArrayList<>();
        odFare.getFares().forEach(fareDto -> {
            Fare fare = new Fare();
            fare.setTicketType(fareDto.getTicketType());
            fare.setFareClass(fareDto.getFareClass());
            fare.setCabinClass(fareDto.getCabinClass());
            fare.setPrice(fareDto.getPrice());
            fare.setRailFare(railFare);
            fares.add(fare);
        });
        railFare.setFares(fares);

        return railFare;
    }
}
