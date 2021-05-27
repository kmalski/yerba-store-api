package com.zti.yerbastore.repository;

import com.zti.yerbastore.model.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticsRepository extends MongoRepository<Statistics, String> {
}
