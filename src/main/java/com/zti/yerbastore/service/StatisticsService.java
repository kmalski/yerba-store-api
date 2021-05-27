package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.InternalServerErrorException;
import com.zti.yerbastore.model.Statistics;
import com.zti.yerbastore.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Transactional(readOnly = true)
    public Statistics find() {
        List<Statistics> stats = statisticsRepository.findAll();

        if (stats.size() != 1)
            throw new InternalServerErrorException("Multiple statistics for website, please perform reset");

        return stats.get(0);
    }

    public Statistics reset() {
        statisticsRepository.deleteAll();

        return statisticsRepository.insert(new Statistics(0));
    }

    public void incrementViews() {
        Statistics stats;
        try {
            stats = find();
        } catch (InternalServerErrorException ex) {
            stats = reset();
        }

        stats.setWebsiteViews(stats.getWebsiteViews() + 1);

        statisticsRepository.save(stats);
    }
}
