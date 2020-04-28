package com.margin.repository;

import com.margin.enums.Status;
import com.margin.entity.ScrapActionDetailsEntity;
import com.margin.entity.SourceMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapActionDetailsEntityRepository extends JpaRepository<ScrapActionDetailsEntity, Long> {
    ScrapActionDetailsEntity findFirstBySourceInfoEntity_IdOrderByVersionDesc(Long scrapSourceId);
    List<ScrapActionDetailsEntity> findAllBySourceMetadataAndVersionOrStatus(SourceMetaData sourceMetaData, Integer version, Status status);
    ScrapActionDetailsEntity findFirstByStatusOrderByEndDateDesc(final Status status);
    ScrapActionDetailsEntity findFirstByStatusOrderByStartDateDesc(final Status status);
    Integer countByStatus(final Status status);


}
