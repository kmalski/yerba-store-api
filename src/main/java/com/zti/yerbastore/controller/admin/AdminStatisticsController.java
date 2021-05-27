package com.zti.yerbastore.controller.admin;

import com.zti.yerbastore.model.Statistics;
import com.zti.yerbastore.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/admin/stats", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminStatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public Statistics getStats() {
        return statisticsService.find();
    }

    @DeleteMapping
    public Statistics resetStats() {
        return statisticsService.reset();
    }
}
