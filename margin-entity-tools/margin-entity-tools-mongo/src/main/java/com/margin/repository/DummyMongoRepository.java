package com.margin.repository;

import com.margin.entity.DummyMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DummyMongoRepository extends MongoRepository<DummyMongoEntity, String> {
}
