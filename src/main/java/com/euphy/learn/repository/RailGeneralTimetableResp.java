package com.euphy.learn.repository;

import com.euphy.learn.model.RailGeneralTimetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RailGeneralTimetableResp extends JpaRepository<RailGeneralTimetable, Integer> {
}
