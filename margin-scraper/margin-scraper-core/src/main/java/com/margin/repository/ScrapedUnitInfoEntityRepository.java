package com.margin.repository;

import com.margin.enums.Status;
import com.margin.entity.ScrapedUnitInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapedUnitInfoEntityRepository extends JpaRepository<ScrapedUnitInfoEntity, Long> {
    List<ScrapedUnitInfoEntity> findBySourceMetaData_IdAndScrapActionDetailsEntity_VersionAndStatus(Long sourceMetaDataId, Integer version, Status status);

}
