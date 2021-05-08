package com.zti.yerbastore.repository;

import com.zti.yerbastore.model.Yerba;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface YerbaRepository extends MongoRepository<Yerba, String> {
}
