package com.zti.yerbastore.aspect;

import com.zti.yerbastore.service.StatisticsService;
import com.zti.yerbastore.service.YerbaService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ViewsAspect {

    private final YerbaService yerbaService;
    private final StatisticsService statisticsService;

    @After(value = "@annotation(YerbaViewsTrigger) && args(id, ..)", argNames = "id")
    public void incrementYerbaViews(String id) {
        yerbaService.incrementViews(id);
    }

    @After("@annotation(WebsiteViewsTrigger)")
    public void incrementYerbaViews() {
        statisticsService.incrementViews();
    }

}