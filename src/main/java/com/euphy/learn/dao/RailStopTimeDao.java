package com.euphy.learn.dao;

import com.euphy.learn.model.RailGeneralTimetable;
import com.euphy.learn.model.RailGeneralTrainInfo;
import com.euphy.learn.model.RailServiceDay;
import com.euphy.learn.model.RailStopTime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RailStopTimeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RailStopTime> getStopTimes(String fromStationId, String toStationId, String searchDay, String searchTime) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RailStopTime> query = builder.createQuery(RailStopTime.class);

        Root<RailStopTime> root = query.from(RailStopTime.class);
        Join<RailStopTime, RailGeneralTimetable> rgtJoin = root.join("generalTimetable", JoinType.INNER);
        Join<RailGeneralTimetable, RailGeneralTrainInfo> rgtiJoin = rgtJoin.join("generalTrainInfo", JoinType.INNER);

        Subquery<Integer> subquery = query.subquery(Integer.class);
        Root<RailGeneralTimetable> gtRoot = subquery.from(RailGeneralTimetable.class);
        Join<RailGeneralTimetable, RailServiceDay> rsdJoin = gtRoot.join("serviceDay", JoinType.INNER);
        subquery.select(gtRoot.get("id"));
        subquery.where(builder.equal(rsdJoin.get(searchDay), 1));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("stationId"), fromStationId));
        predicates.add(builder.greaterThanOrEqualTo(root.get("departureTime"), searchTime));
        predicates.add(builder.in(rgtJoin.get("id")).value(subquery));

        if (fromStationId.compareTo(toStationId) < 0) {
            predicates.add(builder.greaterThanOrEqualTo(rgtiJoin.get("endingStationId"), toStationId));
        }
        else {
            predicates.add(builder.lessThanOrEqualTo(rgtiJoin.get("endingStationId"), toStationId));
        }

        query.select(root);
        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(builder.asc(root.get("departureTime")));

        return entityManager.createQuery(query).getResultList();
    }

}
