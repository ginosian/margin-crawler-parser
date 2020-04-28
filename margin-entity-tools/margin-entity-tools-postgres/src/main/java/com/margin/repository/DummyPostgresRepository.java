package com.margin.repository;

import com.margin.entity.DummyPostgresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyPostgresRepository extends JpaRepository<DummyPostgresEntity, Long> {
}
