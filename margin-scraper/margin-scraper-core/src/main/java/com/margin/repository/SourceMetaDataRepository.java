package com.margin.repository;

import com.margin.enums.Channel;
import com.margin.enums.Country;
import com.margin.entity.SourceMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SourceMetaDataRepository extends JpaRepository<SourceMetaData, Long> {
    Optional<SourceMetaData> findFirstByCountryAndChannel(Country country, Channel channel);
    List<SourceMetaData> findFirstByCountryOrChannel(Country country, Channel channel);
    SourceMetaData getByIdOrCountryAndChannel(Long id, Country country, Channel channel);
}
