package com.margin.repository;

import com.margin.entity.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<Dummy, Long> {
}
