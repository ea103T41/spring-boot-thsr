package com.euphy.learn.tdx;

import com.euphy.learn.tdx.dto.GeneralTimetableGroup;
import com.euphy.learn.tdx.dto.ODFare;
import com.euphy.learn.tdx.dto.Station;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ThsrRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThsrRequestService.class);
    private final TdxClientHelper tdxClientHelper;
    private final ObjectMapper objectMapper;


    @Autowired
    public ThsrRequestService(TdxClientHelper tdxClientHelper, ObjectMapper objectMapper) {
        this.tdxClientHelper = tdxClientHelper;
        this.objectMapper = objectMapper;
    }


    public List<Station> getStations() {
        try {
            String resultJson = tdxClientHelper.fetchData("Station");
            List<Station> railStations = objectMapper.readValue(resultJson, objectMapper
                    .getTypeFactory().constructCollectionType(List.class, Station.class));
            LOGGER.debug("railStations: {}", railStations);
            return railStations;
        } catch (IOException e) {
            LOGGER.error("getStations error", e);
            throw new RuntimeException(e);
        }
    }

    public List<ODFare> getODFares() {
        try {
            String resultJson = tdxClientHelper.fetchData("ODFare");
            List<ODFare> odFares = objectMapper.readValue(resultJson, objectMapper
                    .getTypeFactory().constructCollectionType(List.class, ODFare.class));
            LOGGER.debug("odFares: {}", odFares);
            return odFares;
        } catch (IOException e) {
            LOGGER.error("getODFares error", e);
            throw new RuntimeException(e);
        }
    }

    public List<GeneralTimetableGroup> getGeneralTimetables() {
        try {
            String resultJson = tdxClientHelper.fetchData("GeneralTimetable");
            List<GeneralTimetableGroup> gtGroups =
                    objectMapper.readValue(resultJson, objectMapper
                                .getTypeFactory()
                                .constructCollectionType(List.class, GeneralTimetableGroup.class));
            LOGGER.debug("gtGroups: {}", gtGroups);
            return gtGroups;

        } catch (IOException e) {
            LOGGER.error("getGeneralTimetables error", e);
            throw new RuntimeException(e);
        }
    }

}
