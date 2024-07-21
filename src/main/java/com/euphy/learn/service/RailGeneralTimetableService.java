package com.euphy.learn.service;

import com.euphy.learn.dao.*;
import com.euphy.learn.model.RailGeneralTimetable;
import com.euphy.learn.model.RailGeneralTrainInfo;
import com.euphy.learn.model.RailServiceDay;
import com.euphy.learn.model.RailStopTime;
import com.euphy.learn.repository.RailGeneralTimetableResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RailGeneralTimetableService {

    private final GenericDao genericDao;
    private final RailGeneralTimetableResp railGeneralTimetableResp;

    @Autowired
    public RailGeneralTimetableService(GenericDao genericDao,
                                       RailGeneralTimetableResp railGeneralTimetableResp) {
        this.genericDao = genericDao;
        this.railGeneralTimetableResp = railGeneralTimetableResp;
    }

    public void saveAll(List<RailGeneralTimetable> railGeneralTimetables) {
        railGeneralTimetableResp.saveAll(railGeneralTimetables);
    }

    public void deleteAll() {
        genericDao.deleteAll(RailStopTime.class);
        genericDao.deleteAll(RailGeneralTimetable.class);
        genericDao.deleteAll(RailServiceDay.class);
        genericDao.deleteAll(RailGeneralTrainInfo.class);
    }
}
