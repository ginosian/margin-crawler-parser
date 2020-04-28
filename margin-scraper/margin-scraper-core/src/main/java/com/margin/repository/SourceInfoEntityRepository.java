package com.margin.repository;

import com.margin.entity.SourceInfoEntity;
import com.margin.entity.SourceMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceInfoEntityRepository extends JpaRepository<SourceInfoEntity, Long> {
    List<SourceInfoEntity> findBySourceMetaData(SourceMetaData metaData);
}
