package com.zti.yerbastore.aspect;

import com.zti.yerbastore.service.StatisticsService;
import com.zti.yerbastore.service.YerbaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ViewsAspect {

    private final YerbaService yerbaService;
    private final StatisticsService statisticsService;

    @After(value = "@annotation(YerbaViewsTrigger) && args(id, ..)", argNames = "id")
    public void incrementYerbaViews(String id) {
        log.info("Incrementing views for yerba with id {}", id);

        yerbaService.incrementViews(id);
    }

    @After(value = "@annotation(WebsiteViewsTrigger) && args(name, originCountry, ..)", argNames = "name, originCountry")
    public void incrementYerbaViews(String name, String originCountry) {
        if (name == null && originCountry == null) {
            log.info("Incrementing views for website");

            statisticsService.incrementViews();
        }
    }

}