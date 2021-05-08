package com.zti.yerbastore.repository;

import com.zti.yerbastore.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository  extends MongoRepository<Photo, String> {
}
