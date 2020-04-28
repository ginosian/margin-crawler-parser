package com.margin.repository;

import com.margin.entity.NLPOrganization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NLPOrganizationRepository extends MongoRepository<NLPOrganization, String> {
}
