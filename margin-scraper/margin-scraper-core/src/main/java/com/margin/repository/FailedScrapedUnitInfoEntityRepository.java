package com.margin.repository;

import com.margin.entity.FailedScrapedUnitInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedScrapedUnitInfoEntityRepository extends JpaRepository<FailedScrapedUnitInfoEntity, Long> {
}
