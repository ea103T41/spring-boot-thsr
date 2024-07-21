package com.euphy.learn.controller;

import com.euphy.learn.dto.FareInfo;
import com.euphy.learn.dto.StopTimeDetail;
import com.euphy.learn.dto.StopTimeInfo;
import com.euphy.learn.model.RailStation;
import com.euphy.learn.service.RailFareService;
import com.euphy.learn.service.RailStationService;
import com.euphy.learn.service.RailStopTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {

    private List<RailStation> railStations;
    private final RailStationService railStationService;
    private final RailFareService railFareService;
    private final RailStopTimeService railStopTimeService;

    @Autowired
    public MainController(
            RailStationService railStationService,
            RailFareService railFareService,
            RailStopTimeService railStopTimeService) {
        this.railStationService = railStationService;
        this.railFareService = railFareService;
        this.railStopTimeService = railStopTimeService;
    }

    @GetMapping
    public ModelAndView select() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("select_page");
        return mv;
    }

    @PostMapping(value = "/getRailInfo")
    public ModelAndView getRailInfo(
            /* 1.接收請求參數 - 輸入格式的錯誤處理 */
            @RequestParam("OriginStationID") String originStationID,
            @RequestParam("DestinationStationID") String destinationStationID,
            @RequestParam("TrainDate") String trainDate,
            @RequestParam("startTime") String startTime,
            ModelMap model) {
        ModelAndView mv = new ModelAndView();

        /* 2.開始查詢資料 */
        List<FareInfo> fareInfoList = railFareService.searchFare(originStationID, destinationStationID);
        if (fareInfoList == null) {
            model.addAttribute("errorMessage", "查無票價資料");
            mv.setViewName("select_page");
            return mv;
        }
        List<StopTimeInfo> stopTimeInfoList = railStopTimeService.searchStopTime(
                originStationID, destinationStationID, trainDate, startTime);
        if (stopTimeInfoList.isEmpty()) {
            model.addAttribute("errorMessage", "查無時刻表資料");
            mv.setViewName("select_page");
            return mv;
        }

        /* 3.查詢完成,準備轉交(Send the Success view) */
        model.addAttribute("fareInfoList", fareInfoList);
        model.addAttribute("stopTimeInfoList", stopTimeInfoList);
        mv.setViewName("select_result");
        return mv;
    }

    @PostMapping(value = "/getStopTimeDetail")
    public List<StopTimeDetail> getStopTimeDetail(@RequestParam("gtId") Integer gtId) {
        return railStopTimeService.searchStopTime(railStations, gtId);
    }

    @ModelAttribute("railStations")
    protected List<RailStation> getRailStations() {
        if (railStations == null) {
            railStations = railStationService.getAll();
        }
        return railStations;
    }
}
