package com.euphy.learn.tdx;

import com.euphy.learn.common.RecordElapsedTime;
import com.euphy.learn.mapper.GeneralTimetableMapper;
import com.euphy.learn.mapper.OdFareMapper;
import com.euphy.learn.mapper.StationMapper;
import com.euphy.learn.model.RailFare;
import com.euphy.learn.model.RailGeneralTimetable;
import com.euphy.learn.model.RailStationPosition;
import com.euphy.learn.service.LatestVersionService;
import com.euphy.learn.service.RailFareService;
import com.euphy.learn.service.RailGeneralTimetableService;
import com.euphy.learn.service.RailStationService;
import com.euphy.learn.tdx.dto.GeneralTimetableGroup;
import com.euphy.learn.tdx.dto.ODFare;
import com.euphy.learn.tdx.dto.Station;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InitialDataFetchService implements CommandLineRunner {
    private final ApplicationContext applicationContext;
    private final ThsrRequestService thsrRequestService;
    private final RailStationService railStationService;
    private final RailFareService railFareService;
    private final LatestVersionService latestVersionService;
    private final RailGeneralTimetableService railGeneralTimetableService;
    private final StationMapper stationMapper;
    private final OdFareMapper odFareMapper;
    private final GeneralTimetableMapper gtMapper;

    @Autowired
    public InitialDataFetchService(
            ApplicationContext applicationContext,
            ThsrRequestService thsrRequestService,
            RailStationService railStationService,
            RailFareService railFareService,
            RailGeneralTimetableService railGeneralTimetableService,
            LatestVersionService latestVersionService,
            StationMapper stationMapper,
            OdFareMapper odFareMapper,
            GeneralTimetableMapper gtMapper) {
        this.applicationContext = applicationContext;
        this.thsrRequestService = thsrRequestService;
        this.railStationService = railStationService;
        this.railFareService = railFareService;
        this.railGeneralTimetableService = railGeneralTimetableService;
        this.latestVersionService = latestVersionService;
        this.stationMapper = stationMapper;
        this.odFareMapper = odFareMapper;
        this.gtMapper = gtMapper;
    }

    @Value("${app.runner.enabled}")
    private boolean isRunnerEnabled;

    @Override
    public void run(String... args) {
        if (isRunnerEnabled) {
            applicationContext.getBean(InitialDataFetchService.class).init();
        }
    }

    private static final String COMMAND_LINE_RUNNER = "CommandLineRunner run";
    private static final String GET_STATION = "railStation table init/update";
    private static final String GET_ODFARE = "railFare table init/update";

    public void init() {
        RecordElapsedTime.start(COMMAND_LINE_RUNNER);
        initStation();
        initODFare();
        initGeneralTimetable();
        RecordElapsedTime.end(COMMAND_LINE_RUNNER);
    }

    public void initStation() {
        RecordElapsedTime.start(GET_STATION);

        List<Station> stations = thsrRequestService.getStations();
        if (stations.isEmpty()) {
            throw new RuntimeException("Station list is empty");
        }
        boolean isUpdated = latestVersionService.updateLatestVersion("station", stations.get(0));

        if (isUpdated) {
            railStationService.deleteAll();
            List<RailStationPosition> railStationPositions = stations.stream()
                    .map(stationMapper::toRailStationPosition)
                    .toList();
            railStationService.saveAll(railStationPositions);
        }
        RecordElapsedTime.end(GET_STATION);
    }

    public void initODFare() {
        RecordElapsedTime.start(GET_ODFARE);

        List<ODFare> odFares = thsrRequestService.getODFares();
        if (odFares.isEmpty()) {
            throw new RuntimeException("ODFare list is empty");
        }
        boolean isUpdated = latestVersionService.updateLatestVersion("odFare", odFares.get(0));

        if (isUpdated) {
            railFareService.deleteAll();
            List<RailFare> railFares = odFares.stream()
                    .map(this.odFareMapper::toEntity)
                    .collect(Collectors.toList());
            railFareService.saveAll(railFares);
        }
        RecordElapsedTime.end(GET_ODFARE);
    }

    public void initGeneralTimetable() {
		RecordElapsedTime.start("GeneralTimetable DB init/update");

		List<GeneralTimetableGroup> gtGroups = thsrRequestService.getGeneralTimetables();
        if (gtGroups.isEmpty()) {
            throw new RuntimeException("GeneralTimetableGroups is empty");
        }
        boolean isUpdated = latestVersionService.updateLatestVersion("generalTimetable", gtGroups.get(0));

        if (isUpdated) {
            railGeneralTimetableService.deleteAll();
            List<RailGeneralTimetable> railGeneralTimetables = gtGroups.stream()
                    .map(gtg -> gtMapper.toEntity(gtg.getGeneralTimetable()))
                    .toList();
            railGeneralTimetableService.saveAll(railGeneralTimetables);
        }
		RecordElapsedTime.end("GeneralTimetable DB init/update");
    }

}
