package com.zti.yerbastore.service;

import com.zti.yerbastore.model.Statistics;
import com.zti.yerbastore.repository.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        statisticsService = new StatisticsService(statisticsRepository);
    }

    @Test
    void testFind() {
        when(statisticsRepository.findAll()).thenReturn(Collections.singletonList(new Statistics(10)));

        Statistics result = statisticsService.find();

        assertEquals(10, result.getWebsiteViews());
    }

    @Test
    void testIncrementViews() {
        Statistics testStats = new Statistics(10);
        when(statisticsRepository.findAll()).thenReturn(Collections.singletonList(testStats));

        statisticsService.incrementViews();

        assertEquals(11, testStats.getWebsiteViews());
    }
}