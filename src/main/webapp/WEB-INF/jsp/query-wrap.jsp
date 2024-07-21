<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 class="time-title">時刻表與票價查詢</h3>
<div class="query-wrap">
    <FORM METHOD="post" ACTION="/thsr/getRailInfo" id="form">
    <p class="query-title">請選擇查詢條件</p>
    <div class="query-panel">
        <div class="spot-wrap">
            <div class="spot-content">
                <p>起程站</p>
                <select id="select-start" name="OriginStationID">
                    <c:forEach var="rs" items="${railStations}" >
                        <option value="${rs.stationId}"
                                ${rs.stationId.equals(param.OriginStationID) ? 'selected' : ''} >
                                ${rs.stationName}
                    </c:forEach>
                </select>
            </div>
            <div class="spot-content">
                <p>到達站</p>
                <select id="select-end" name="DestinationStationID">
                    <c:forEach var="rs" items="${railStations}" >
                        <option value="${rs.stationId}"
                                ${rs.stationId.equals(param.DestinationStationID) ? 'selected' : ''} >
                                ${rs.stationName}
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="query-rule-wrap">
            <div class="time-content">
                <p>去程日期</p>
                <input class="query-date" type="date" id="query-date" name="TrainDate" value="${param.TrainDate}">
            </div>
            <div class="time-content">
                <p>去程時刻</p>
                <input class="query-time" type="time" id="query-time" name="startTime" value="${param.startTime}">
            </div>
        </div>
    </div>
    <div id="query-button" class="query-button">立即查詢</div>
    </FORM>
</div>
