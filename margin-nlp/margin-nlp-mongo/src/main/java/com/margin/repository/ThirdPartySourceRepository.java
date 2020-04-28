package com.margin.repository;

import com.margin.entity.thirdparty.source.entity.ThirdPartySourceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThirdPartySourceRepository extends MongoRepository<ThirdPartySourceEntity, String> {
}
