package com.margin.repository;
/*
 *   @author ironman
 *   @since  11/1/18
 */

import com.margin.enums.Country;
import com.margin.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    CountryEntity getByName(String name);
}
