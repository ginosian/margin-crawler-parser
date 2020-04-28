package com.margin.repository;
/*
 *   @author ironman
 *   @since  11/9/18
 */

import com.margin.enums.Country;
import com.margin.entity.CountryEntity;
import com.margin.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findAllByName(String name);
}
