package com.margin.repository;

import com.margin.entity.DummyElasticEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DummyElasticRepository extends ElasticsearchRepository<DummyElasticEntity, String> {
}
